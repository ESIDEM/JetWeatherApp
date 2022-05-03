package com.xtremepixel.jetweatherapp.widgets

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.MoreVert
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp

@Preview
@Composable
fun WeatherAppBar(
    title: String = "Title",
    icon: ImageVector? = null,
    isHomeScreen: Boolean = true,
    elevation: Dp = 0.dp, onActionClicked: () -> Unit = {},
    onButtonClicked: () -> Unit = {}
) {

    TopAppBar(
        title = {
            Text(text = title)
        },
        actions = {
                  if (isHomeScreen){
                      IconButton(onClick = { /*TODO*/ }) {
                          Icon(imageVector = Icons.Default.Search,
                              contentDescription = "Search Icon" )
                      }

                      IconButton(onClick = { /*TODO*/ }) {

                          Icon(imageVector = Icons.Rounded.MoreVert,
                              contentDescription = "More dots")
                      }
                  }
        },
        navigationIcon = {},
        backgroundColor = Color.Transparent,
        elevation = elevation
    )
}