package com.xtremepixel.jetweatherapp.widgets

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.xtremepixel.jetweatherapp.model.Favourite
import com.xtremepixel.jetweatherapp.navigation.WeatherAppScreen
import com.xtremepixel.jetweatherapp.screens.favourite.FavouriteVM

@Composable
fun WeatherAppBar(
    navController: NavController,
    title: String = "Title",
    icon: ImageVector? = null,
    isHomeScreen: Boolean = true,
    elevation: Dp = 0.dp,
    favouriteVM: FavouriteVM = hiltViewModel(),
    onActionClicked: () -> Unit = {},
    onButtonClicked: () -> Unit = {}
) {

    val showDialog = remember {
        mutableStateOf(false)
    }

    val showIt = remember {
        mutableStateOf(false)
    }

    val context = LocalContext.current

    ShowDropDownMenu(showDialog = showDialog, navController = navController)

    TopAppBar(
        title = {
            Text(
                text = title,
                color = MaterialTheme.colors.onSecondary,
                style = TextStyle(fontWeight = FontWeight.Bold, fontSize = 15.sp)
            )
        },
        actions = {
            if (isHomeScreen) {
                IconButton(onClick = { onActionClicked.invoke() }) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search Icon"
                    )
                }

                IconButton(onClick = { showDialog.value = true }) {

                    Icon(
                        imageVector = Icons.Rounded.MoreVert,
                        contentDescription = "More dots"
                    )
                }
            } else {
                Box {

                }
            }
        },
        navigationIcon = {
            if (icon != null) {
                Icon(imageVector = icon,
                    contentDescription = "Navigation Arrow",
                    tint = MaterialTheme.colors.onSecondary,
                    modifier = Modifier.clickable {
                        onButtonClicked.invoke()
                    })
            }
            val isFavourite = favouriteVM.favList.collectAsState().value.filter {
                (it.city == title.split(",")[0])
            }

            if (isHomeScreen && isFavourite.isEmpty()) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Fav Icon",
                    modifier = Modifier
                        .scale(0.9f)
                        .clickable {
                            favouriteVM
                                .insertFav(
                                    Favourite(
                                        city = title.split(",")[0],
                                        country = title.split(",")[1]
                                    )
                                )
                                .run {
                                    showIt.value = true
                                }
                        }, tint = Color.Red
                )
            } else {
                showIt.value = false
            }

            ShowToast(context = context, showIt = showIt)
        },
        backgroundColor = Color.Transparent,
        elevation = elevation
    )
}

@Composable
fun ShowToast(context: Context, showIt: MutableState<Boolean>) {
    if (showIt.value) {
        Toast.makeText(context, "City Added to favourite", Toast.LENGTH_LONG).show()
    }
}

@Composable
fun ShowDropDownMenu(
    showDialog: MutableState<Boolean>,
    navController: NavController
) {
    val items = listOf("About", "Favorites", "Settings")
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentSize(Alignment.TopEnd)
            .absolutePadding(top = 45.dp, right = 20.dp)
    ) {

        DropdownMenu(
            expanded = showDialog.value,
            onDismissRequest = { showDialog.value = false },
            modifier = Modifier
                .width(140.dp)
                .background(Color.White)
        ) {
            items.forEachIndexed { index, s ->
                DropdownMenuItem(onClick = {
                    showDialog.value = false
                }) {

                    Icon(
                        imageVector = when (s) {
                            "About" -> Icons.Default.Info
                            "Favorites" -> Icons.Default.FavoriteBorder
                            else -> Icons.Default.Settings
                        }, contentDescription = null, tint = Color.LightGray
                    )
                    Text(
                        text = s,
                        modifier = Modifier.clickable {
                            navController.navigate(
                                route = when (s) {
                                    "About" -> WeatherAppScreen.AboutScreen.name
                                    "Favorites" -> WeatherAppScreen.FavoriteScreen.name
                                    else -> WeatherAppScreen.SettingScreen.name
                                }
                            )
                        }, fontWeight = FontWeight.W300
                    )
                }
            }
        }

    }

}
