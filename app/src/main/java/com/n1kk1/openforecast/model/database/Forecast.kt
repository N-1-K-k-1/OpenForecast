package com.n1kk1.openforecast.model.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "forecast")
data class Forecast(
    @PrimaryKey
    @ColumnInfo(name = "name")
    val name: String,

    @ColumnInfo(name = "todayTemp")
    val todayTemp: Double,
    @ColumnInfo(name = "todayDescription")
    val todayDescription: String,
    @ColumnInfo(name = "todayIcon")
    val todayIcon: String,

    @ColumnInfo(name = "todayPressure")
    val todayPressure: Int,
    @ColumnInfo(name = "todayHumidity")
    val todayHumidity: Int,
    @ColumnInfo(name = "todayWindSpeed")
    val todayWindSpeed: Double,

    @ColumnInfo(name = "firstDay")
    val firstDay: Long,
    @ColumnInfo(name = "tempFirstDay")
    val tempFirstDay: Double,
    @ColumnInfo(name = "iconFirstDay")
    val iconFirstDay: String,

    @ColumnInfo(name = "secondDay")
    val secondDay: Long,
    @ColumnInfo(name = "tempSecondDay")
    val tempSecondDay: Double,
    @ColumnInfo(name = "iconSecondDay")
    val iconSecondDay: String,

    @ColumnInfo(name = "thirdDay")
    val thirdDay: Long,
    @ColumnInfo(name = "tempThirdDay")
    val tempThirdDay: Double,
    @ColumnInfo(name = "iconThirdDay")
    val iconThirdDay: String,

    @ColumnInfo(name = "fourthDay")
    val fourthDay: Long,
    @ColumnInfo(name = "tempFourthDay")
    val tempFourthDay: Double,
    @ColumnInfo(name = "iconFourthDay")
    val iconFourthDay: String,

    @ColumnInfo(name = "fifthDay")
    val fifthDay: Long,
    @ColumnInfo(name = "tempFifthDay")
    val tempFifthDay: Double,
    @ColumnInfo(name = "iconFifthDay")
    val iconFifthDay: String,

    @ColumnInfo(name = "sixthDay")
    val sixthDay: Long,
    @ColumnInfo(name = "tempSixthDay")
    val tempSixthDay: Double,
    @ColumnInfo(name = "iconSixthDay")
    val iconSixthDay: String,

    @ColumnInfo(name = "seventhDay")
    val seventhDay: Long,
    @ColumnInfo(name = "tempSeventhDay")
    val tempSeventhDay: Double,
    @ColumnInfo(name = "iconSeventhDay")
    val iconSeventhDay: String,

    @ColumnInfo(name = "lastUpdate")
    val lastUpdate: Long,

    @ColumnInfo(name = "sunrise")
    val sunrise: Int,
    @ColumnInfo(name = "sunset")
    val sunset: Int
)