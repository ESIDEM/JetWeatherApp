package com.xtremepixel.jetweatherapp.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.xtremepixel.jetweatherapp.R
import com.xtremepixel.jetweatherapp.data.ResultWrapper
import com.xtremepixel.jetweatherapp.model.Weather
import com.xtremepixel.jetweatherapp.model.WeatherItem
import com.xtremepixel.jetweatherapp.model.WeatherObject
import com.xtremepixel.jetweatherapp.utils.Constants
import com.xtremepixel.jetweatherapp.utils.formatDate
import com.xtremepixel.jetweatherapp.utils.formatDateTime
import com.xtremepixel.jetweatherapp.utils.formatDecimals
import com.xtremepixel.jetweatherapp.widgets.WeatherAppBar
import okhttp3.internal.wait

@Composable
fun HomeScreen(navController: NavController, homeScreenVM: HomeScreenViewModel = viewModel()) {

    val weatherData = produceState<ResultWrapper<Weather, Boolean, Exception>>(
        initialValue = ResultWrapper(loading = true)
    ) {
        value = homeScreenVM.getWeatherData("Uyo")
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
            elevation = 1.dp
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

@Composable
fun WeekForecast(data: Weather) {
    LazyColumn {
        items(items = data.list) { item ->
            WeekForecastItem(weatherItem = item)
        }
    }

}

@Composable
fun WeekForecastItem(weatherItem: WeatherItem) {
    Surface(
        modifier = Modifier.padding(4.dp),
        elevation = 1.dp, shape = RectangleShape, color = Color.White
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 4.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {

            Text(
                text = formatDate(weatherItem.dt).split(",")[0],
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(start = 2.dp)
            )
            WeatherStateImage(imageUrl = "${Constants.IMAGE_URL}${weatherItem.weather[0].icon}${Constants.IMAGE_EX}")
            Surface(
                shape = RoundedCornerShape(corner = CornerSize(32.dp)),
                color = Color.Yellow
            ) {
                Text(
                    text = weatherItem.weather[0].description,
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.padding(4.dp)
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "${formatDecimals(weatherItem.temp.max)}º",
                    style = MaterialTheme.typography.caption,
                    color = Color.Blue
                )
                Text(
                    text = "${formatDecimals(weatherItem.temp.min)}º",
                    style = MaterialTheme.typography.caption,
                    modifier = Modifier.padding(start = 2.dp),
                    color = Color.LightGray
                )
            }
        }
    }
}

@Composable
fun SunSetRow(data: WeatherItem) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Row(modifier = Modifier.padding(4.dp)) {

            Icon(
                painter = painterResource(id = R.drawable.sunrise),
                contentDescription = null,
                modifier = Modifier.size(30.dp)
            )

            Text(
                text = formatDateTime(data.sunrise),
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(start = 2.dp)
            )

        }
        Row(modifier = Modifier.padding(4.dp)) {

            Text(text = formatDateTime(data.sunset), style = MaterialTheme.typography.caption)

            Icon(
                painter = painterResource(id = R.drawable.sunset),
                contentDescription = null,
                modifier = Modifier.size(30.dp)
            )

        }
    }
}

@Composable
fun HumidityRow(data: WeatherItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        Row(modifier = Modifier.padding(4.dp)) {
            Icon(
                painter = painterResource(id = R.drawable.humidity),
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )

            Text(
                text = "${data.humidity} %",
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(start = 2.dp)
            )

        }
        Row(modifier = Modifier.padding(4.dp)) {
            Icon(
                painter = painterResource(id = R.drawable.pressure),
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )

            Text(text = "${data.pressure} psi", style = MaterialTheme.typography.caption)

        }
        Row(modifier = Modifier.padding(4.dp)) {
            Icon(
                painter = painterResource(id = R.drawable.wind),
                contentDescription = null,
                modifier = Modifier.size(20.dp)
            )

            Text(
                text = "${data.humidity} mph",
                style = MaterialTheme.typography.caption,
                modifier = Modifier.padding(start = 2.dp)
            )

        }
    }
}

@Composable
fun WeatherStateImage(imageUrl: String) {
    Image(
        painter = rememberImagePainter(imageUrl),
        contentDescription = "Weather icon", modifier = Modifier.size(80.dp)
    )
}
