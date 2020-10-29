package com.n1kk1.openforecast.ui.forecast

interface ForecastView {
    fun showForecast(forecast: ForecastEntity)
    fun showError(error: String)
}