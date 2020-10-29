package com.n1kk1.openforecast.model.response

import com.google.gson.annotations.SerializedName

class CurrentWeatherResponse {

    val coord : Coord? = null
    val weather : List<Weather>? = null
    val base : String? = null
    val main : Main? = null
    val visibility : Int = 0
    val wind : Wind? = null
    val clouds : Clouds? = null
    val dt : Int = 0
    val sys : Sys? = null
    val timezone : Int = 0
    val id : Int = 0
    val name : String? = null
    val cod : Int = 0

    data class Coord (
        val lon : Double,
        val lat : Double
    )

    data class Weather (
        val id : Int,
        val main : String,
        val description : String,
        val icon : String
    )

    data class Main (
        val temp : Double,
        val pressure : Int,
        val humidity : Int,
        val temp_min : Double,
        val temp_max : Double
    )

    data class Wind (
        val speed : Double,
        val deg : Int
    )

    data class Clouds (
        val all : Int
    )

    data class Sys (
        val type : Int,
        val id : Int,
        val message : Double,
        val country : String,
        val sunrise : Int,
        val sunset : Int
    )
}