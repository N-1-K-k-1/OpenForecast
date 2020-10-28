package com.n1kk1.openforecast

import androidx.room.*
import com.n1kk1.openforecast.model.database.City

@Dao
interface CityDao {
    @Query("SELECT * FROM city")
    fun getAll(): List<City>

    @Query("SELECT * FROM city WHERE name = :name")
    fun getByName(name: String): City

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(city: City)

    @Delete
    fun delete(city: City)
}