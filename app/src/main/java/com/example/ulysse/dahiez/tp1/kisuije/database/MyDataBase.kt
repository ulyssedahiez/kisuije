package com.example.ulysse.dahiez.tp1.kisuije.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.ulysse.dahiez.tp1.kisuije.database.dao.CompetitorDao
import com.example.ulysse.dahiez.tp1.kisuije.database.dao.GameDao
import com.example.ulysse.dahiez.tp1.kisuije.database.dao.PlayerDao
import com.example.ulysse.dahiez.tp1.kisuije.database.dao.StarDao
import com.example.ulysse.dahiez.tp1.kisuije.database.entities.Competitor
import com.example.ulysse.dahiez.tp1.kisuije.database.entities.Game
import com.example.ulysse.dahiez.tp1.kisuije.database.entities.Player
import com.example.ulysse.dahiez.tp1.kisuije.database.entities.Star

@Database(entities = [Competitor::class, Game::class, Player::class, Star::class], version = 1, exportSchema = false)
abstract class MyDatabase : RoomDatabase() {
    abstract fun competitorDao(): CompetitorDao
    abstract fun gameDao(): GameDao
    abstract fun playerDao(): PlayerDao
    abstract fun starDao(): StarDao

    companion object {
        @Volatile
        private var INSTANCE: MyDatabase? = null

        fun getMyDatabase(context: Context): MyDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyDatabase::class.java,
                    "my_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}
