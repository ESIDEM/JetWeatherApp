package com.xtremepixel.jetweatherapp.screens

import android.view.animation.OvershootInterpolator
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.xtremepixel.jetweatherapp.R
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.draw.scale
import com.xtremepixel.jetweatherapp.navigation.WeatherAppScreen
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(navController: NavController) {
    val scale = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = true, block = {
        scale.animateTo(targetValue = 0.9f, animationSpec = tween(
            durationMillis = 800,
            easing = {
                OvershootInterpolator(8f).getInterpolation(it)
            }
        ))

        delay(2000L)
        navController.navigate(WeatherAppScreen.HomeScreen.name)
    })
    Surface(
        modifier = Modifier
            .padding(15.dp)
            .size(330.dp)
            .scale(scale.value),
        shape = CircleShape,
        border = BorderStroke(width = 2.dp, color = Color.LightGray),
        color = Color.White
    ) {

        Column(
            modifier = Modifier.padding(1.dp), horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Image(
                modifier = Modifier.size(100.dp),
                painter = painterResource(id = R.drawable.sun),
                contentDescription = " Sun Image"
            )
            Text(
                text = "Find the Sun?",
                style = MaterialTheme.typography.h5,
                color = Color.LightGray
            )
        }
    }
}