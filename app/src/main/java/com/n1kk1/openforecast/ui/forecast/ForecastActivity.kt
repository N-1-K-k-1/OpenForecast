package com.n1kk1.openforecast.ui.forecast

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.n1kk1.openforecast.R
import com.n1kk1.openforecast.model.database.City
import kotlinx.android.synthetic.main.activity_forecast.*
import kotlin.math.roundToInt

class ForecastActivity : AppCompatActivity(), ForecastView {

    private lateinit var toolbar: androidx.appcompat.app.ActionBar
    private lateinit var city: City
    private lateinit var presenter: ForecastPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forecast)

        presenter = ForecastPresenter(this, this as ForecastView)

        toolbar = supportActionBar!!
        toolbar.hide()

        city = intent.extras?.getParcelable<City>("name") as City

        presenter.refreshForecast(city)
    }

    override fun showForecast(forecast: ForecastEntity) {

        city_name.text = forecast.name

        weather_description.text = forecast.todayDescription
        weather_today_temp.text = forecast.todayTemp
        weather_today_icon.setImageResource(forecast.todayIcon)


        weather_today_pressure.text = forecast.todayPressure
        weather_today_humidity.text = forecast.todayHumidity
        weather_today_windSpeed.text = forecast.todayWindSpeed

        weather_first_day.text = forecast.firstDay
        temp_weather_first_day.text = forecast.tempFirstDay
        icon_weather_first_day.setImageResource(forecast.iconFirstDay)

        weather_second_day.text = forecast.secondDay
        temp_weather_second_day.text = forecast.tempSecondDay
        icon_weather_second_day.setImageResource(forecast.iconSecondDay)

        weather_third_day.text = forecast.thirdDay
        temp_weather_third_day.text = forecast.tempThirdDay
        icon_weather_third_day.setImageResource(forecast.iconThirdDay)

        weather_fourth_day.text = forecast.fourthDay
        temp_weather_fourth_day.text = forecast.tempFourthDay
        icon_weather_fourth_day.setImageResource(forecast.iconFourthDay)

        weather_fifth_day.text = forecast.fifthDay
        temp_weather_fifth_day.text = forecast.tempFifthDay
        icon_weather_fifth_day.setImageResource(forecast.iconFifthDay)

        weather_sixth_day.text = forecast.sixthDay
        temp_weather_sixth_day.text = forecast.tempSixthDay
        icon_weather_sixth_day.setImageResource(forecast.iconSixthDay)

        weather_seventh_day.text = forecast.seventhDay
        temp_weather_seventh_day.text = forecast.tempSeventhDay
        icon_weather_seventh_day.setImageResource(forecast.iconSeventhDay)

        weather_last_update.text = getString(R.string.last_update, forecast.lastUpdate)

        today_sunrise.text = forecast.sunrise
        today_sunset.text = forecast.sunset
    }

    override fun showError(error: String) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
    }
}