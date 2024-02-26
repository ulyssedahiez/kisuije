package com.example.ulysse.dahiez.tp1.kisuije.database.dao


import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.ulysse.dahiez.tp1.kisuije.database.entities.Star

@Dao
interface StarDao {
    @Insert
    fun insert(star: Star?)

    @get:Query("SELECT * FROM star")
    val allStars: List<Star?>?
}
