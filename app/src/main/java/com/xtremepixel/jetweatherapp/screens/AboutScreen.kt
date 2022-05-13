package com.xtremepixel.jetweatherapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.xtremepixel.jetweatherapp.widgets.WeatherAppBar

@Composable
fun AboutScreen(
    navController: NavController
){
   
    Scaffold(
        topBar = {
            WeatherAppBar(navController = navController,
                icon = Icons.Default.ArrowBack,
                elevation = 1.dp,
                isHomeScreen = false,
            title = "About"){
                navController.popBackStack()
            }
        }
    ) {
        Column(modifier = Modifier.padding(16.dp).fillMaxWidth().fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
            
            Text(text = "This App was built during a bootcamp to learn Compose")
        }
    }
}