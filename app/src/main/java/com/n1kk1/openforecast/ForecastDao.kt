package com.n1kk1.openforecast

import androidx.room.*
import com.n1kk1.openforecast.model.database.Forecast

@Dao
interface ForecastDao {
    @Query("SELECT * FROM forecast WHERE name = :name")
    fun getByName(name: String): Forecast

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(forecast: Forecast)

    @Update
    fun update(forecast: Forecast)

    @Query("DELETE FROM forecast WHERE name = :name")
    fun delete(name: String)
}