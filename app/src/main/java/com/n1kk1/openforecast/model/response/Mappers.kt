package com.n1kk1.openforecast.model.response

import com.n1kk1.openforecast.model.database.City
import com.n1kk1.openforecast.model.database.Forecast
import retrofit2.Response
import java.util.*

fun Response<ForecastResponse>.toForecast(city: City): Forecast {
            return Forecast(
                name = city.name,
                todayTemp = city.temp,
                todayDescription = city.description,
                todayIcon = city.icon,

                todayPressure = city.pressure,
                todayHumidity = city.humidity,
                todayWindSpeed = city.windSpeed,

                firstDay = body()?.list!![0].dt.toLong(),
                tempFirstDay= body()?.list!![0].main.temp,
                iconFirstDay = body()?.list!![0].weather[0].icon,

                secondDay = body()?.list!![1].dt.toLong(),
                tempSecondDay = body()?.list!![1].main.temp,
                iconSecondDay = body()?.list!![1].weather[0].icon,

                thirdDay = body()?.list!![2].dt.toLong(),
                tempThirdDay = body()?.list!![2].main.temp,
                iconThirdDay = body()?.list!![2].weather[0].icon,

                fourthDay = body()?.list!![3].dt.toLong(),
                tempFourthDay = body()?.list!![3].main.temp,
                iconFourthDay = body()?.list!![3].weather[0].icon,

                fifthDay = body()?.list!![4].dt.toLong(),
                tempFifthDay = body()?.list!![4].main.temp,
                iconFifthDay = body()?.list!![4].weather[0].icon,

                sixthDay = body()?.list!![5].dt.toLong(),
                tempSixthDay = body()?.list!![5].main.temp,
                iconSixthDay = body()?.list!![5].weather[0].icon,

                seventhDay = body()?.list!![6].dt.toLong(),
                tempSeventhDay = body()?.list!![6].main.temp,
                iconSeventhDay = body()?.list!![6].weather[0].icon,

                lastUpdate = Date().time,
                sunset = city.sunset,
                sunrise = city.sunrise

            )
}

fun Response<CurrentWeatherResponse>.toCity(): City? {
    return body()?.name?.let {name ->
        body()?.main?.temp?.let { temp ->
            body()?.main?.pressure?.let { pressure ->
                body()?.main?.humidity?.let { humidity ->
                    body()?.sys?.sunrise?.let { sunrise ->
                        body()?.sys?.sunset?.let { sunset ->
                            body()?.wind?.speed?.let { windSpeed ->
                                body()?.weather?.get(0)?.description?.let { description ->
                                    body()?.weather?.get(0)?.icon?.let { icon ->
                                        City(
                                            name = name,
                                            temp = temp,
                                            pressure = pressure,
                                            humidity = humidity,
                                            sunrise = sunrise,
                                            sunset = sunset,
                                            windSpeed = windSpeed,
                                            description = description,
                                            icon = icon
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}