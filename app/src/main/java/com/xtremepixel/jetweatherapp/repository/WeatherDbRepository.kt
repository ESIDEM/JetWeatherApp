package com.xtremepixel.jetweatherapp.repository

import com.xtremepixel.jetweatherapp.data.WeatherDao
import com.xtremepixel.jetweatherapp.model.Favourite
import com.xtremepixel.jetweatherapp.model.Unit
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WeatherDbRepository @Inject constructor(private val weatherDao: WeatherDao) {

    fun getFavourites(): Flow<List<Favourite>> = weatherDao.getFavourites()

    suspend fun insertFav(favourite: Favourite) = weatherDao.insertFavourite(favourite)

    suspend fun deleteAllFav() = weatherDao.deleteAllFavourite()

    suspend fun deleteFav(favourite: Favourite) = weatherDao.deleteFavourite(favourite)

    fun getUnit(): Flow<List<Unit>> = weatherDao.getUnit()

    suspend fun insertUnit(unit: Unit) = weatherDao.insertUnit(unit)
}