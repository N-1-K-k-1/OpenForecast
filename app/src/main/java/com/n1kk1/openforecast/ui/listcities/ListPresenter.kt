package com.n1kk1.openforecast.ui.listcities

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.n1kk1.openforecast.R
import com.n1kk1.openforecast.utils.AsyncTask.executeAsyncTask
import com.n1kk1.openforecast.api.Common
import com.n1kk1.openforecast.api.WeatherService
import com.n1kk1.openforecast.model.response.CurrentWeather
import com.n1kk1.openforecast.model.database.AppDatabase.Companion.getInstance
import com.n1kk1.openforecast.model.database.City
import com.n1kk1.openforecast.utils.GeoData
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread
import retrofit2.Call
import retrofit2.Response


class ListPresenter(private val context: Context, private val homeView: ListView) {

    private lateinit var mService: WeatherService
    private lateinit var cities: List<City>
    private lateinit var geoData: GeoData

    fun getAllCities(){
        (context as AppCompatActivity).lifecycleScope.executeAsyncTask(onPreExecute = {
            // ... runs in Main Thread
        }, doInBackground = {
            if (getInstance(context).cityDao().getAll().isEmpty()) {
                addCity("Sankt-Peterburg")
                addCity("Moscow")
            }
            getInstance(context).cityDao().getAll()
        }, onPostExecute = {
            // runs in Main Thread
            // ... here "it" is the data returned from "doInBackground"
            homeView.showListCities(it)
            println(it)
        })

    }


    fun addCity(city: String){
        mService = Common.weatherService
        geoData = GeoData(context)

            mService.getCurrentWeather(city, geoData.getLanguage()).enqueue(
                object : retrofit2.Callback<CurrentWeather>{
                    override fun onResponse(call: Call<CurrentWeather>, response: Response<CurrentWeather>) {
                        if (response.isSuccessful) {
                            response.body()?.main?.temp?.let { temp ->
                                response.body()?.name?.let {
                                    City(
                                        it,
                                        temp
                                    )
                                }
                            }?.let { city ->
                                doAsync {
                                    getInstance(context).cityDao().insert(city)
                                    val results = getInstance(context).cityDao().getAll()
                                    println(results)
                                    uiThread {
                                        homeView.showListCities(results)
                                    }
                                }
                            }
                        }
                        else {
                            when(response.code()){
                                400 -> homeView.showError((context as AppCompatActivity).getString(R.string.wrongApi))
                                403 -> homeView.showError((context as AppCompatActivity).getString(R.string.wrongApi))
                                404 -> homeView.showError((context as AppCompatActivity).getString(R.string.notFound))
                                else -> homeView.showError((context as AppCompatActivity).getString(R.string.failedToLoad))
                            }
                        }
                    }

                    override fun onFailure(call: Call<CurrentWeather>, t: Throwable) {
                        homeView.showError((context as AppCompatActivity).getString(R.string.failedToLoad))
                    }
                }
            )
    }

    fun removeCity(city: City){

        (context as AppCompatActivity).lifecycleScope.executeAsyncTask(onPreExecute = {
            // ... runs in Main Thread
        }, doInBackground = {
            getInstance(context).cityDao().delete(city)
            getInstance(context).cityDao().getAll()
        }, onPostExecute = {
            // runs in Main Thread
            // ... here "it" is the data returned from "doInBackground"
            homeView.showListCities(it)
        })
    }

    fun clickOnCity(city: City){

    }

}