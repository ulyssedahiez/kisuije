package com.example.ulysse.dahiez.tp1.kisuije.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.ulysse.dahiez.tp1.kisuije.database.entities.Game

@Dao
interface GameDao {
    @Insert
    fun insert(game: Game)

    @Query("SELECT * FROM game")
    fun  getAllGames(): List<Game?>?
}
