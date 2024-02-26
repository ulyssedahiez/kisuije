package com.example.ulysse.dahiez.tp1.kisuije.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Star(
    @PrimaryKey val id_star: Int,
    val name: String
)
