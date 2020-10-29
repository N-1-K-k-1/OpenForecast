package com.n1kk1.openforecast.ui.forecast

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.util.Log
import com.n1kk1.openforecast.api.Common
import com.n1kk1.openforecast.api.WeatherService
import com.n1kk1.openforecast.model.database.AppDatabase.Companion.getInstance
import com.n1kk1.openforecast.model.database.City
import com.n1kk1.openforecast.model.database.toForecastEntity
import com.n1kk1.openforecast.model.response.CurrentWeatherResponse
import com.n1kk1.openforecast.model.response.ForecastResponse
import com.n1kk1.openforecast.model.response.toCity
import com.n1kk1.openforecast.model.response.toForecast
import com.n1kk1.openforecast.utils.GeoData
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import retrofit2.Call
import retrofit2.Response

class ForecastPresenter(private val context: Context, private val forecastView: ForecastView) {

    private lateinit var mService: WeatherService
    private lateinit var geoData: GeoData


    fun refreshForecast(city: City) {
        mService = Common.weatherService
        geoData = GeoData(context)

        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        connectivityManager.let {
            it.registerNetworkCallback(
                NetworkRequest.Builder().build(),
                object : ConnectivityManager.NetworkCallback() {
                    override fun onAvailable(network: Network) {
                        Log.e("NETWORK", "Available")

                        mService.getCurrentWeather(city.name, geoData.getLanguage()).enqueue(
                            object : retrofit2.Callback<CurrentWeatherResponse> {
                                override fun onResponse(
                                    call: Call<CurrentWeatherResponse>,
                                    response: Response<CurrentWeatherResponse>
                                ) {
                                    if (response.isSuccessful) {
                                        response.toCity()
                                    } else {
                                        Log.e("Error", response.message())
                                    }
                                }

                                override fun onFailure(
                                    call: Call<CurrentWeatherResponse>,
                                    t: Throwable
                                ) {
                                    Log.e("Error", "err")
                                }
                            }
                        )

                        mService.getForecast(city.name, geoData.getLanguage()).enqueue(
                            object : retrofit2.Callback<ForecastResponse> {
                                override fun onResponse(
                                    call: Call<ForecastResponse>,
                                    response: Response<ForecastResponse>
                                ) {
                                    if (response.isSuccessful) {
                                        doAsync {
                                            getInstance(context).forecastDao().insert(response.toForecast(city))
                                            println(
                                                getInstance(context).forecastDao()
                                                    .getByName(city.name)
                                            )
                                            val results = getInstance(context).forecastDao().getByName(city.name)
                                            uiThread {
                                                forecastView.showForecast(results.toForecastEntity())
                                                println(results)
                                            }
                                        }
                                    } else {
                                        Log.e("Error", response.message())
                                    }
                                }

                                override fun onFailure(
                                    call: Call<ForecastResponse>,
                                    t: Throwable
                                ) {
                                    Log.e("Error", "err")
                                }
                            }
                        )

                    }
                }
            )
        }
    }

}