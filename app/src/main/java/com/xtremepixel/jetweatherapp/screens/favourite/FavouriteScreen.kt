package com.xtremepixel.jetweatherapp.screens.favourite

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.xtremepixel.jetweatherapp.model.Favourite
import com.xtremepixel.jetweatherapp.navigation.WeatherAppScreen
import com.xtremepixel.jetweatherapp.widgets.WeatherAppBar


@Composable
fun FavouriteScreen(
    navController: NavController,
    favouriteVM: FavouriteVM = hiltViewModel()
) {
    Scaffold(topBar = {
        WeatherAppBar(
            navController = navController,
            favouriteVM = favouriteVM,
            icon = Icons.Default.ArrowBack,
            isHomeScreen = false,
            elevation = 1.dp, title = "Favourite Cities"
        ) {
            navController.popBackStack()
        }
    }) {

        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        ) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {

                val favList = favouriteVM.favList.collectAsState().value
                LazyColumn {
                    items(items = favList) {

                        FavouriteItem(it, navController = navController, favouriteVM = favouriteVM)
                    }
                }
            }
        }
    }
}

@Composable
fun FavouriteItem(it: Favourite, navController: NavController, favouriteVM: FavouriteVM) {
    Surface(
        modifier = Modifier
            .padding(3.dp)
            .fillMaxWidth()
            .height(50.dp)
            .clickable {
                navController.navigate(WeatherAppScreen.HomeScreen.name + "/${it.city}")
            }, shape = RectangleShape, elevation = 1.dp
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Text(text = it.city)
            Text(text = it.country)
            Icon(
                imageVector = Icons.Default.Delete,
                contentDescription = "Delete fave icon",
                modifier = Modifier.clickable {
                    favouriteVM.deleteFav(it)
                })
        }
    }
}
