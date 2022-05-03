package com.xtremepixel.jetweatherapp.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.xtremepixel.jetweatherapp.screens.HomeScreen
import com.xtremepixel.jetweatherapp.screens.HomeScreenViewModel
import com.xtremepixel.jetweatherapp.screens.SplashScreen

@Composable
fun WeatherAppNavigation() {
    
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = WeatherAppScreen.SplashScreen.name ){
        composable(WeatherAppScreen.SplashScreen.name){
            SplashScreen(navController = navController)
        }

        composable(WeatherAppScreen.HomeScreen.name){
            val homeScreenVM = hiltViewModel<HomeScreenViewModel>()
            HomeScreen(navController = navController, homeScreenVM)
        }

    }
}