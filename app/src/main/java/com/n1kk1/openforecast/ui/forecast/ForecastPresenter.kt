package com.n1kk1.openforecast.ui.forecast

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkRequest
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.n1kk1.openforecast.R
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
import com.n1kk1.openforecast.utils.CheckNetwork.isInternetAvailable
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import retrofit2.Call
import retrofit2.Response

class ForecastPresenter(private val context: Context, private val forecastView: ForecastView) {

    private lateinit var mService: WeatherService
    private lateinit var geoData: GeoData


    fun refreshForecast(city: City) {
        mService = Common.weatherService
        geoData = GeoData()

        forecastView.showSpinner()
        if (!isInternetAvailable(context)) {
            Log.e("NETWORK", "UNAVAILABLE")
            doAsync {
                val results = getInstance(context).forecastDao().getByName(city.name)
                @Suppress("SENSELESS_COMPARISON")
                if (results != null) {
                    uiThread {
                        forecastView.showForecast(results.toForecastEntity())
                        forecastView.hideSpinner()
                        forecastView.showError((context as AppCompatActivity).getString(R.string.cant_update))
                    }
                } else {
                    uiThread {
                        forecastView.showError((context as AppCompatActivity).getString(R.string.failedToLoad))
                        forecastView.hideSpinner()
                        context.finish()
                    }
                }
            }
        } else {
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
                            when (response.code()) {
                                400 -> forecastView.showError(
                                    (context as AppCompatActivity).getString(
                                        R.string.wrongApi
                                    )
                                )
                                403 -> forecastView.showError(
                                    (context as AppCompatActivity).getString(
                                        R.string.wrongApi
                                    )
                                )
                                404 -> forecastView.showError(
                                    (context as AppCompatActivity).getString(
                                        R.string.notFound
                                    )
                                )
                                else -> forecastView.showError(
                                    (context as AppCompatActivity).getString(
                                        R.string.failedToLoad
                                    )
                                )
                            }
                        }
                        forecastView.hideSpinner()
                    }

                    override fun onFailure(
                        call: Call<CurrentWeatherResponse>,
                        t: Throwable
                    ) {
                        forecastView.showError(
                            (context as AppCompatActivity).getString(
                                R.string.failedToLoad
                            )
                        )
                        forecastView.hideSpinner()
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
                                getInstance(context).forecastDao()
                                    .insert(response.toForecast(city))
                                val results = getInstance(context).forecastDao()
                                    .getByName(city.name)
                                uiThread {
                                    forecastView.showForecast(results.toForecastEntity())
                                }
                            }
                        } else {
                            when (response.code()) {
                                400 -> forecastView.showError(
                                    (context as AppCompatActivity).getString(
                                        R.string.wrongApi
                                    )
                                )
                                403 -> forecastView.showError(
                                    (context as AppCompatActivity).getString(
                                        R.string.wrongApi
                                    )
                                )
                                404 -> forecastView.showError(
                                    (context as AppCompatActivity).getString(
                                        R.string.notFound
                                    )
                                )
                                else -> forecastView.showError(
                                    (context as AppCompatActivity).getString(
                                        R.string.failedToLoad
                                    )
                                )
                            }
                        }
                        forecastView.hideSpinner()
                    }

                    override fun onFailure(
                        call: Call<ForecastResponse>,
                        t: Throwable
                    ) {
                        forecastView.showError(
                            (context as AppCompatActivity).getString(
                                R.string.failedToLoad
                            )
                        )
                        forecastView.hideSpinner()
                    }
                }
            )
            forecastView.hideSpinner()
        }
    }
}