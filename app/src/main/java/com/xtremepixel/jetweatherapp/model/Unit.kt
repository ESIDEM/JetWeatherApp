package com.xtremepixel.jetweatherapp.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "unit_table")
data class Unit(
    @NonNull
    @PrimaryKey
    val unit: String
)
