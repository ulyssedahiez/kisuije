package com.example.ulysse.dahiez.tp1.kisuije.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.ulysse.dahiez.tp1.kisuije.database.entities.Competitor

@Dao
interface CompetitorDao {
    @Insert
    fun insert(competitor: Competitor)

    @Query("SELECT * FROM competitor")
    fun getAllCompetitors(): List<Competitor?>?
}
