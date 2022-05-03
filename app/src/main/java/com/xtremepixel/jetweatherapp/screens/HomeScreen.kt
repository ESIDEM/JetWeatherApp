package com.xtremepixel.jetweatherapp.screens

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.xtremepixel.jetweatherapp.data.ResultWrapper
import com.xtremepixel.jetweatherapp.model.Weather

@Composable
fun HomeScreen(navController: NavController, homeScreenVM: HomeScreenViewModel = viewModel()){

    Surface() {
        ShowData(homeScreenVM = homeScreenVM)
    }
}

@Composable
fun ShowData(homeScreenVM: HomeScreenViewModel){

    val weatherData = produceState<ResultWrapper<Weather, Boolean, Exception>>(
        initialValue = ResultWrapper(loading = true)){
        value = homeScreenVM.getWeatherData("Uyo")
    }.value

    if (weatherData.loading == true){
        CircularProgressIndicator()
    } else if (weatherData.data != null){
        Text(text = "Home Screen ${weatherData.data?.city?.country}")
    }

}