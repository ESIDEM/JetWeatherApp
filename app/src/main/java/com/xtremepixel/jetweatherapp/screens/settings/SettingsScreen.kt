package com.xtremepixel.jetweatherapp.screens.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.xtremepixel.jetweatherapp.widgets.WeatherAppBar


@Composable
fun SettingsScreen(
    navController: NavController, settingsVM: SettingsVM = hiltViewModel()
) {

    val unitToggleState = remember { mutableStateOf(false) }
    val measurements = listOf("Imperial (F)", "Metric (C)")
    val choiceState = remember {
        mutableStateOf("")
    }
    Scaffold(topBar = {
        WeatherAppBar(
            navController = navController,
            title = "Settings",
            icon = Icons.Default.ArrowBack,
            isHomeScreen = false,
            elevation = 1.dp
        )
    }) {

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        ) {

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Change unit of measurement",
                    modifier = Modifier.padding(bottom = 15.dp)
                )

                IconToggleButton(checked = unitToggleState.value.not(),
                    onCheckedChange = {
                        unitToggleState.value = it.not()
                        if (unitToggleState.value) {
                            choiceState.value = measurements[0]
                        } else choiceState.value = measurements[1]
                    }, modifier = Modifier
                        .fillMaxWidth(0.5f)
                        .clip(RectangleShape)
                        .padding(5.dp)
                        .background(color = Color.LightGray)) {
                    Text(text = if (unitToggleState.value) "Fahrenheit ºF" else "Celsius ºC")
                }

                Button(
                    onClick = { /*TODO*/ },
                    modifier = Modifier
                        .padding(3.dp)
                        .align(CenterHorizontally), shape = RoundedCornerShape(corner = CornerSize(32.dp))
                ) {
                    Text(text = "Save", modifier = Modifier.padding(3.dp))
                }
            }

        }

    }
}