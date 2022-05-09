package com.xtremepixel.jetweatherapp.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun WeatherAppBar(
    navController: NavController,
    title: String = "Title",
    icon: ImageVector? = null,
    isHomeScreen: Boolean = true,
    elevation: Dp = 0.dp,
    onActionClicked: () -> Unit = {},
    onButtonClicked: () -> Unit = {}
) {

    val showDialog = remember {
        mutableStateOf(false)
    }

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
        },
        backgroundColor = Color.Transparent,
        elevation = elevation
    )
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
                    Text(text = s,
                        modifier = Modifier.clickable { }, fontWeight = FontWeight.W300)
                }
            }
        }

    }

}
