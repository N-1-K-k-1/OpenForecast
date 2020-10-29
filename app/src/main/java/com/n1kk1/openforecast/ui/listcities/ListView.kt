package com.n1kk1.openforecast.ui.listcities

import com.n1kk1.openforecast.model.database.City

interface ListView {
    fun showListCities(cities: List<City>)
    fun startWeatherActivity(city: City)
    fun showError(error: String)
}