package com.example.ulysse.dahiez.tp1.kisuije.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Competitor(
    @PrimaryKey(autoGenerate = true) val id_competitor: Long = 0,
    val id_player: Int,
    val id_star: Int,
    val nb_point: Int,
    val id_game: Int
)
