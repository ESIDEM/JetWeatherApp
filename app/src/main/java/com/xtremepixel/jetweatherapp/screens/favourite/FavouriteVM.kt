package com.xtremepixel.jetweatherapp.screens.favourite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xtremepixel.jetweatherapp.model.Favourite
import com.xtremepixel.jetweatherapp.repository.WeatherDbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavouriteVM @Inject constructor(private val repository: WeatherDbRepository) : ViewModel() {

    private val _favList = MutableStateFlow<List<Favourite>>(emptyList())

    val favList = _favList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            repository.getFavourites().distinctUntilChanged().collect{
                _favList.value = it
            }
        }
    }

    fun insertFav(favourite: Favourite) = viewModelScope.launch {
        repository.insertFav(favourite)
    }

    fun deleteFav(favourite: Favourite) = viewModelScope.launch {
        repository.deleteFav(favourite)
    }
}