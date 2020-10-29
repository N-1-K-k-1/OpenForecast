package com.n1kk1.openforecast.model.database

import android.os.Parcel
import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "city")
data class City(
    @PrimaryKey
    @ColumnInfo(name = "name")
    val name: String,
    @ColumnInfo(name = "temp")
    val temp: Double,
    @ColumnInfo(name = "pressure")
    val pressure : Int,
    @ColumnInfo(name = "humidity")
    val humidity : Int,
    @ColumnInfo(name = "sunrise")
    val sunrise : Int,
    @ColumnInfo(name = "sunset")
    val sunset : Int,
    @ColumnInfo(name = "windSpeed")
    val windSpeed: Double,
    @ColumnInfo(name = "description")
    val description: String,
    @ColumnInfo(name = "icon")
    val icon: String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readDouble(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readInt(),
        parcel.readDouble(),
        parcel.readString().toString(),
        parcel.readString().toString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeDouble(temp)
        parcel.writeInt(pressure)
        parcel.writeInt(humidity)
        parcel.writeInt(sunrise)
        parcel.writeInt(sunset)
        parcel.writeDouble(windSpeed)
        parcel.writeString(description)
        parcel.writeString(icon)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<City> {
        override fun createFromParcel(parcel: Parcel): City {
            return City(parcel)
        }

        override fun newArray(size: Int): Array<City?> {
            return arrayOfNulls(size)
        }
    }
}