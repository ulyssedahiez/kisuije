package com.example.ulysse.dahiez.tp1.kisuije.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "game")
data class Game(
    @PrimaryKey val id_game: Int,
    val nb_round: Int
)
