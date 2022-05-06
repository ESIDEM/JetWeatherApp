package com.xtremepixel.jetweatherapp.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.xtremepixel.jetweatherapp.data.ResultWrapper
import com.xtremepixel.jetweatherapp.model.Weather
import com.xtremepixel.jetweatherapp.navigation.WeatherAppScreen
import com.xtremepixel.jetweatherapp.utils.formatDate
import com.xtremepixel.jetweatherapp.utils.formatDecimals
import com.xtremepixel.jetweatherapp.widgets.*

@Composable
fun HomeScreen(
    navController: NavController,
    homeScreenVM: HomeScreenViewModel = viewModel(),
    city: String?
) {

    val weatherData = produceState<ResultWrapper<Weather, Boolean, Exception>>(
        initialValue = ResultWrapper(loading = true)
    ) {
        value = homeScreenVM.getWeatherData(city.toString())
    }.value

    if (weatherData.loading == true) {
        CircularProgressIndicator()
    } else if (weatherData.data != null) {
        HomeScreenScaffold(weather = weatherData.data!!, navController = navController)
    }
}

@Composable
fun HomeScreenScaffold(weather: Weather, navController: NavController) {

    Scaffold(topBar = {
        WeatherAppBar(
            title = "${weather.city.name}, ${weather.city.country}",
            elevation = 1.dp,
            onActionClicked = {
                navController.navigate(route = WeatherAppScreen.SearchScreen.name)
            }
        )
    }) {
        MainContent(data = weather)
    }
}

@Composable
fun MainContent(data: Weather) {

    val weatherItem = data.list[0]
    val imageUrl = "https://openweathermap.org/img/wn/${weatherItem.weather[0].icon}.png"
    Column(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = formatDate(weatherItem.dt), style = MaterialTheme.typography.caption,
            color = MaterialTheme.colors.onSecondary,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier.padding(6.dp)
        )

        Surface(
            modifier = Modifier
                .size(180.dp)
                .padding(4.dp),
            shape = CircleShape,
            color = Color.Yellow
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                WeatherStateImage(imageUrl)
                Text(
                    text = formatDecimals(weatherItem.temp.day) + "º",
                    style = MaterialTheme.typography.h4,
                    fontWeight = FontWeight.ExtraBold
                )
                Text(text = weatherItem.weather[0].main, fontStyle = FontStyle.Italic)
            }
        }

        HumidityRow(data = weatherItem)

        Divider()

        SunSetRow(data = weatherItem)

        Text(text = "This week", fontWeight = FontWeight.Bold)

        WeekForecast(data)
    }
}
