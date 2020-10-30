package com.n1kk1.openforecast.model.database

import com.n1kk1.openforecast.R
import com.n1kk1.openforecast.ui.forecast.ForecastEntity
import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToInt

fun Forecast.toForecastEntity(): ForecastEntity {

    fun getTodayImage(icon: String) = when (icon) {
        "01d" -> R.drawable.ic_sun_140
        "01n" -> R.drawable.ic_moon_140
        "02d" -> R.drawable.ic_partly_cloudy_d_140
        "02n" -> R.drawable.ic_partly_cloudy_140
        "03d", "03n" -> R.drawable.ic_mostly_cloudy_1_140
        "04d", "04n" -> R.drawable.ic_mostly_cloudy_2_140
        "09d", "09n" -> R.drawable.ic_heavy_rain_140
        "10d" -> R.drawable.ic_heavy_rain_day_140
        "10n" -> R.drawable.ic_heavy_rain_night_140
        "11d" -> R.drawable.ic_thunderstorm_day_140
        "11n" -> R.drawable.ic_thunderstorm_night_140
        "13d" -> R.drawable.ic_snow_day_140
        "13n" -> R.drawable.ic_snow_night_140
        "50d", "50n" -> R.drawable.ic_mist_140
        else -> R.drawable.ic_baseline_close_24
    }

    fun getImageForEveryWeekDay(icon: String) = when (icon) {
        "01d", "01n" -> R.drawable.ic_sun_32
        "02d", "02n" -> R.drawable.ic_partly_cloudy_d_32
        "03d", "03n" -> R.drawable.ic_mostly_cloudy_32
        "04d", "04n" -> R.drawable.ic_mostly_cloudy_2_32
        "09d", "09n" -> R.drawable.ic_heavy_rain_32
        "10d", "10n" -> R.drawable.ic_heavy_rain_day_32
        "11d", "11n" -> R.drawable.ic_thunderstorm_day_32
        "13d", "13n" -> R.drawable.ic_snow_day_32
        "50d", "50n" -> R.drawable.ic_mist_32
        else -> R.drawable.ic_baseline_close_24

    }

    val formatDay = SimpleDateFormat("E", Locale.getDefault())
    val formatLastUpdate = SimpleDateFormat("d MMM HH:mm", Locale.getDefault())
    val formatSunTime = SimpleDateFormat("HH:mm", Locale.getDefault())

    return ForecastEntity(
        name = name,
        todayTemp = todayTemp.roundToInt().toString(),
        todayDescription = todayDescription,
        todayIcon = getTodayImage(todayIcon),

        todayPressure = todayPressure.toString(),
        todayHumidity = todayHumidity.toString(),
        todayWindSpeed = todayWindSpeed.toString(),

        firstDay = formatDay.format(Date(firstDay * 1000)),
        tempFirstDay = tempFirstDay.roundToInt().toString(),
        iconFirstDay = getImageForEveryWeekDay(iconFirstDay),

        secondDay = formatDay.format(Date(secondDay * 1000 + 86400000)),
        tempSecondDay = tempSecondDay.roundToInt().toString(),
        iconSecondDay = getImageForEveryWeekDay(iconSecondDay),

        thirdDay = formatDay.format(Date(thirdDay * 1000 + 86400000 * 2)),
        tempThirdDay = tempThirdDay.roundToInt().toString(),
        iconThirdDay = getImageForEveryWeekDay(iconThirdDay),

        fourthDay = formatDay.format(Date(fourthDay * 1000 + 86400000 * 3)),
        tempFourthDay = tempFourthDay.roundToInt().toString(),
        iconFourthDay = getImageForEveryWeekDay(iconFourthDay),

        fifthDay = formatDay.format(Date(fifthDay * 1000 + 86400000 * 4)),
        tempFifthDay = tempFifthDay.roundToInt().toString(),
        iconFifthDay = getImageForEveryWeekDay(iconFifthDay),

        sixthDay = formatDay.format(Date(sixthDay * 1000 + 86400000 * 5)),
        tempSixthDay = tempSixthDay.roundToInt().toString(),
        iconSixthDay = getImageForEveryWeekDay(iconSixthDay),

        seventhDay = formatDay.format(Date(seventhDay * 1000 + 86400000 * 6)),
        tempSeventhDay = tempSeventhDay.roundToInt().toString(),
        iconSeventhDay = getImageForEveryWeekDay(iconSeventhDay),

        lastUpdate = formatLastUpdate.format(Date(lastUpdate)),
        sunrise = formatSunTime.format(Date(sunrise.toLong() * 1000)),
        sunset = formatSunTime.format(Date(sunset.toLong() * 1000))
    )
}