package com.xtremepixel.jetweatherapp.screens

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.xtremepixel.jetweatherapp.data.ResultWrapper
import com.xtremepixel.jetweatherapp.model.Weather
import com.xtremepixel.jetweatherapp.widgets.WeatherAppBar

@Composable
fun HomeScreen(navController: NavController, homeScreenVM: HomeScreenViewModel = viewModel()){

    val weatherData = produceState<ResultWrapper<Weather, Boolean, Exception>>(
        initialValue = ResultWrapper(loading = true)){
        value = homeScreenVM.getWeatherData("Uyo")
    }.value

    if (weatherData.loading == true){
        CircularProgressIndicator()
    } else if (weatherData.data != null){
        HomeScreenScaffold(weather = weatherData.data!!, navController = navController)
    }
}

@Composable
fun HomeScreenScaffold(weather: Weather, navController: NavController){

Scaffold(topBar = {
    WeatherAppBar(title = "Lagos, Nigeria", elevation = 1.dp)
}) {
    MainContent(data = weather)
}
}

@Composable
fun MainContent(data: Weather) {

}
