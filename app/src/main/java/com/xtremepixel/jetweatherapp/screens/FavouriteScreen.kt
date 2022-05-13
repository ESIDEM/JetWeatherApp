package com.xtremepixel.jetweatherapp.screens

import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavController


@Composable
fun FavouriteScreen(
    navController: NavController
){
    androidx.compose.material.Surface {
        Text(text = "Favour Screen")
    }
}