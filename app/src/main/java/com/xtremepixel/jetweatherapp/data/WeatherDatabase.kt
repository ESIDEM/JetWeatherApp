package com.xtremepixel.jetweatherapp.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.xtremepixel.jetweatherapp.model.Favourite

@Database(entities = [Favourite::class], version = 1, exportSchema = false)
abstract class WeatherDatabase: RoomDatabase() {

    abstract fun weatherDao(): WeatherDao
}