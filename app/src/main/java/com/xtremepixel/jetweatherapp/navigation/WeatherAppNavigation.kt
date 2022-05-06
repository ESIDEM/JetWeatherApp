package com.xtremepixel.jetweatherapp.navigation

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.xtremepixel.jetweatherapp.screens.HomeScreen
import com.xtremepixel.jetweatherapp.screens.HomeScreenViewModel
import com.xtremepixel.jetweatherapp.screens.SearchScreen
import com.xtremepixel.jetweatherapp.screens.SplashScreen

@Composable
fun WeatherAppNavigation() {
    
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = WeatherAppScreen.SplashScreen.name ){
        composable(WeatherAppScreen.SplashScreen.name){
            SplashScreen(navController = navController)
        }

        val route = WeatherAppScreen.HomeScreen.name
        composable("$route/{city}", arguments = listOf(navArgument(name = "city"){
            type = NavType.StringType
        })){

            it.arguments?.getString("city").let {
                val homeScreenVM = hiltViewModel<HomeScreenViewModel>()
                HomeScreen(navController = navController, homeScreenVM, city = it)
            }
        }

        composable(WeatherAppScreen.SearchScreen.name){
            val homeScreenVM = hiltViewModel<HomeScreenViewModel>()
            SearchScreen(navController = navController)
        }

    }
}