package com.example.ulysse.dahiez.tp1.kisuije.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.ulysse.dahiez.tp1.kisuije.database.entities.Player

@Dao
interface PlayerDao {
    @Insert
    fun insert(player: Player?)

    @get:Query("SELECT * FROM player")
    val allPlayers: List<Player?>?
}
