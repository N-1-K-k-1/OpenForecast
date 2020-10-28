package com.n1kk1.openforecast.api

object Common {
    private const val BASE_URL = "https://api.openweathermap.org/"

    val weatherService: WeatherService = RetrofitClient.getClient(BASE_URL).create(WeatherService::class.java)

}