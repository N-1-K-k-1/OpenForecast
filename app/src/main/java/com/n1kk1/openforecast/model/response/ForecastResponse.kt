package com.n1kk1.openforecast.model.response

class ForecastResponse {
    val cod: Int = 0
    val message: Int = 0
    val cnt: Int = 0
    val list: kotlin.collections.List<List>? = null
    val city: City? = null

    data class List(
        val dt: Int,
        val main: Main,
        val weather: kotlin.collections.List<Weather>,
        val clouds: Clouds,
        val wind: Wind,
        val sys: Sys,
        val dt_txt: String
    ) {
        data class Main(
            val temp: Double,
            val temp_min: Double,
            val temp_max: Double,
            val pressure: Int,
            val sea_level: Int,
            val grnd_level: Int,
            val humidity: Int,
            val temp_kf: Double
        )

        data class Weather(
            val id: Int,
            val main: String,
            val description: String,
            val icon: String
        )

        data class Clouds(
            val all: Int
        )

        data class Wind(
            val speed: Double,
            val deg: Int
        )

        data class Sys(
            val pod: String
        )
    }

    data class City(
        val id: Int,
        val name: String,
        val coord: Coord,
        val country: String,
        val population: Int,
        val timezone: Int,
        val sunrise: Int,
        val sunset: Int
    ) {
        data class Coord(
            val lat: Double,
            val lon: Double
        )
    }
}