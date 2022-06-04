package com.xtremepixel.jetweatherapp.data

import androidx.room.*
import com.xtremepixel.jetweatherapp.model.Favourite
import com.xtremepixel.jetweatherapp.model.Unit
import kotlinx.coroutines.flow.Flow

@Dao
interface WeatherDao {

    @Query("SELECT * from fav_table")
    fun getFavourites(): Flow<List<Favourite>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFavourite(favourite: Favourite)

    @Query("DELETE from fav_table")
    suspend fun deleteAllFavourite()

    @Delete
    suspend fun deleteFavourite(favourite: Favourite)

    @Query("SELECT * from unit_table")
    fun getUnit(): Flow<List<Unit>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUnit(unit: Unit)
}