package com.xtremepixel.jetweatherapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.xtremepixel.jetweatherapp.navigation.WeatherAppScreen
import com.xtremepixel.jetweatherapp.widgets.WeatherAppBar

@Composable
fun SearchScreen(navController: NavController) {
    Scaffold(topBar = {
        WeatherAppBar(
            icon = Icons.Default.ArrowBack,
            elevation = 1.dp,
            isHomeScreen = false,
            title = "Search...",
            navController = navController
        ) {
            navController.popBackStack()
        }
    }) {

        Surface() {

            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.padding(top = 32.dp)
            ) {
                SearchForm(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .align(Alignment.CenterHorizontally)
                ) {
                    navController.navigate(route = WeatherAppScreen.HomeScreen.name + "/$it")
                }
            }
        }
    }
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchForm(
    modifier: Modifier = Modifier,
    onSearch: (String) -> Unit = {}
) {
    val searchQueryState = rememberSaveable { mutableStateOf("") }
    val keyboardController = LocalSoftwareKeyboardController.current
    val valid = remember(searchQueryState.value) {
        searchQueryState.value.trim().isNotEmpty()
    }
    Column() {
        CommonTextField(
            valueState = searchQueryState,
            placeHolder = "Uyo",
            onAction = KeyboardActions {
                if (valid.not()) return@KeyboardActions
                onSearch(searchQueryState.value.trim())
                searchQueryState.value = ""
                keyboardController?.hide()
            }
        )
    }

}

@Composable
fun CommonTextField(
    valueState: MutableState<String>,
    placeHolder: String,
    keyboardType: KeyboardType = KeyboardType.Text,
    imeAction: ImeAction = ImeAction.Next,
    onAction: KeyboardActions = KeyboardActions.Default
) {

    OutlinedTextField(value = valueState.value, onValueChange = {
        valueState.value = it
    }, label = {
        Text(text = placeHolder)
    }, maxLines = 1, singleLine = true,
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType, imeAction = imeAction),
        keyboardActions = onAction,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Color.Blue,
            cursorColor = Color.Black
        ), shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 10.dp)
    )

}
