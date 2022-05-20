package com.xtremepixel.jetweatherapp.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "fav_table")
data class Favourite(
    @NonNull
    @PrimaryKey
    val city: String,
    val country: String
)
