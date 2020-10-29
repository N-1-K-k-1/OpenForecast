package com.n1kk1.openforecast.ui.forecast

class ForecastEntity(
    val name: String,

    val todayTemp: String,
    val todayDescription: String,
    val todayIcon: Int,

    val todayPressure: String,
    val todayHumidity: String,
    val todayWindSpeed: String,

    val firstDay: String,
    val tempFirstDay: String,
    val iconFirstDay: Int,

    val secondDay: String,
    val tempSecondDay: String,
    val iconSecondDay: Int,

    val thirdDay: String,
    val tempThirdDay: String,
    val iconThirdDay: Int,

    val fourthDay: String,
    val tempFourthDay: String,
    val iconFourthDay: Int,

    val fifthDay: String,
    val tempFifthDay: String,
    val iconFifthDay: Int,

    val sixthDay: String,
    val tempSixthDay: String,
    val iconSixthDay: Int,

    val seventhDay: String,
    val tempSeventhDay: String,
    val iconSeventhDay: Int,

    val lastUpdate: String,

    val sunrise: String,
    val sunset: String
) {
}
