package com.example.ulysse.dahiez.tp1.kisuije.database.entities

import androidx.room.Entity


import androidx.room.PrimaryKey

@Entity
data class Player(
    @PrimaryKey val id_player: Int,
    val name: String,
    val nb_game: Int,
    val total_point: Int
)
