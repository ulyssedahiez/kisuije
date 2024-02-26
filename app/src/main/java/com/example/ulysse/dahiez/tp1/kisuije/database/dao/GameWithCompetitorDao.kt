package com.example.ulysse.dahiez.tp1.kisuije.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.ulysse.dahiez.tp1.kisuije.database.entities.Competitor
import com.example.ulysse.dahiez.tp1.kisuije.database.entities.GameWithCompetitors

@Dao
interface GameWithCompetitorDao {
    @Insert
    fun insert(gameWithCompetitors: GameWithCompetitors)


    @Transaction
    @Query("SELECT * FROM GameWithCompetitors")
    fun getGameWithCompetitorDao(): List<Competitor?>?
}