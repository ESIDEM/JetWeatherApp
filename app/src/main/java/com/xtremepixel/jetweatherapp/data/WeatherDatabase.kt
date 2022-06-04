package com.xtremepixel.jetweatherapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.xtremepixel.jetweatherapp.model.Favourite
import com.xtremepixel.jetweatherapp.model.Unit

@Database(entities = [Favourite::class, Unit::class], version = 2, exportSchema = false)
abstract class WeatherDatabase: RoomDatabase() {

    abstract fun weatherDao(): WeatherDao
}