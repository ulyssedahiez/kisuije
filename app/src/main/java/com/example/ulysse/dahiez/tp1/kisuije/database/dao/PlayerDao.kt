package com.example.ulysse.dahiez.tp1.kisuije.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Delete
import com.example.ulysse.dahiez.tp1.kisuije.database.entities.Player

@Dao
interface PlayerDao {
    @Insert
    fun insert(player: Player)

    @Query("SELECT * FROM Player")
    fun getAllPlayers(): List<Player?>?

    @Query("SELECT * FROM Player WHERE name = :name")
    fun getPlayerByName(name: String): Player?

    //Get all playerName
    @Query("SELECT name FROM Player")
    fun getAllPlayerName(): List<String?>?

    @Delete
    fun delete(player: Player)
}
