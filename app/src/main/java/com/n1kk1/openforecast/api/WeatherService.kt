package com.n1kk1.openforecast.api

import com.n1kk1.openforecast.model.response.CurrentWeatherResponse
import com.n1kk1.openforecast.model.response.ForecastResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface WeatherService {

    @Headers("X-Api-Key: a3acd5958a56c350cd41ccb2306f8fb5")
    @GET("/data/2.5/weather?")
    fun getCurrentWeather(@Query("q") cityName: String, @Query("lang") lang: String, @Query("units") units: String = "metric"): Call<CurrentWeatherResponse>

    @Headers("X-Api-Key: a3acd5958a56c350cd41ccb2306f8fb5")
    @GET("/data/2.5/forecast?")
    fun getForecast(@Query("q") cityName: String, @Query("lang") lang: String, @Query("units") units: String = "metric", @Query("cnt") cnt: Int = 7): Call<ForecastResponse>
}