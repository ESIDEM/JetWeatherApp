package com.xtremepixel.jetweatherapp.widgets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.rememberImagePainter
import com.xtremepixel.jetweatherapp.R
import com.xtremepixel.jetweatherapp.model.Weather
import com.xtremepixel.jetweatherapp.model.WeatherItem
import com.xtremepixel.jetweatherapp.utils.Constants
import com.xtremepixel.jetweatherapp.utils.formatDate
import com.xtremepixel.jetweatherapp.utils.formatDateTime
import com.xtremepixel.jetweatherapp.utils.formatDecimals

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
