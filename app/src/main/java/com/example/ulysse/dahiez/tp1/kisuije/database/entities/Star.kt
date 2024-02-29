package com.example.ulysse.dahiez.tp1.kisuije.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Star(
    @PrimaryKey(autoGenerate = true) val id_star: Long = 0,
    val name: String
)
