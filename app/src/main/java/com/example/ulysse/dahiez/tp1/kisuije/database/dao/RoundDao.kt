package com.example.ulysse.dahiez.tp1.kisuije.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.ulysse.dahiez.tp1.kisuije.database.entities.Round

@Dao
interface RoundDao {
    @Insert
    fun insert(round: Round?)

    @get:Query("SELECT * FROM round")
    val allRounds: List<Round?>?
}
