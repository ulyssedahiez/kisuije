package com.example.ulysse.dahiez.tp1.kisuije.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.ulysse.dahiez.tp1.kisuije.database.entities.Game

@Dao
interface GameDao {
    @Insert
    fun insert(game: Game?)

    @get:Query("SELECT * FROM game")
    val allGames: List<Game?>?
}
