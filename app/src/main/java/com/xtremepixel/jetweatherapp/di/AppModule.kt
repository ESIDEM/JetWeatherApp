package com.xtremepixel.jetweatherapp.di

import android.content.Context
import androidx.room.Room
import com.xtremepixel.jetweatherapp.data.WeatherDao
import com.xtremepixel.jetweatherapp.data.WeatherDatabase
import com.xtremepixel.jetweatherapp.network.WeatherApi
import com.xtremepixel.jetweatherapp.utils.Constants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideWeatherDao(weatherDatabase: WeatherDatabase): WeatherDao =
        weatherDatabase.weatherDao()

    @Provides
    @Singleton
    fun provideWeatherDb(@ApplicationContext context: Context): WeatherDatabase =
        Room.databaseBuilder(context, WeatherDatabase::class.java, "weather_db")
            .fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideWeatherApi(): WeatherApi {

        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(WeatherApi::class.java)
    }
}