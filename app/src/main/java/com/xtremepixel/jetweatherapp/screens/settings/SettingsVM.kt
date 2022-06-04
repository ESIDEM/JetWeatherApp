package com.xtremepixel.jetweatherapp.screens.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.xtremepixel.jetweatherapp.model.Unit
import com.xtremepixel.jetweatherapp.repository.WeatherDbRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsVM @Inject constructor(
    private val weatherDbRepository: WeatherDbRepository
) : ViewModel() {

    private val _unitList = MutableStateFlow<List<Unit>>(emptyList())
    val unitList = _unitList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {

            weatherDbRepository.getUnit().distinctUntilChanged().collect{
                _unitList.value = it
            }

        }
    }

    fun insertUnit(unit: Unit) = viewModelScope.launch {
        weatherDbRepository.insertUnit(unit)
    }
}