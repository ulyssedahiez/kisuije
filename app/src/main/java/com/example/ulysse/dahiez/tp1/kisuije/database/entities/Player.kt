package com.example.ulysse.dahiez.tp1.kisuije.database.entities

import androidx.room.Entity


import androidx.room.PrimaryKey

@Entity
data class Player(
    @PrimaryKey(autoGenerate = true) val id_player: Long = 0,
    val name: String,
    val nb_game: Int,
    val total_point: Int
)
