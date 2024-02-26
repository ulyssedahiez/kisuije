package com.example.ulysse.dahiez.tp1.kisuije.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.ulysse.dahiez.tp1.kisuije.database.Converters
import com.example.ulysse.dahiez.tp1.kisuije.database.entities.Player

@Entity(tableName = "game")
@TypeConverters(Converters::class)
data class Game(
    @PrimaryKey val id_game: Int,
    val list_player: List<Player>,
    val list_round: List<Round>,
    val nb_round: Int
)
