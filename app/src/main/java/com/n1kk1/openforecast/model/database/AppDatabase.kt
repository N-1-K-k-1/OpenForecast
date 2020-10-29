package com.n1kk1.openforecast.model.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.n1kk1.openforecast.CityDao
import com.n1kk1.openforecast.ForecastDao

@Database(entities = [City::class, Forecast::class], version = 4)
abstract class AppDatabase : RoomDatabase() {
    abstract fun cityDao(): CityDao
    abstract fun forecastDao(): ForecastDao

    companion object {
        @JvmStatic
        @Synchronized
        fun getInstance(context: Context): AppDatabase {
            if (instance == null) {
                instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    DB_NAME
                ).fallbackToDestructiveMigration().build()
            }

            return instance!!
        }

        fun destroyDB() {
            instance = null
        }

        @Volatile
        private var instance: AppDatabase? = null
        private const val DB_NAME = "cities"
    }
}