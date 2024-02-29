package com.example.ulysse.dahiez.tp1.kisuije.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.ulysse.dahiez.tp1.kisuije.database.dao.CompetitorDao
import com.example.ulysse.dahiez.tp1.kisuije.database.dao.GameDao
import com.example.ulysse.dahiez.tp1.kisuije.database.dao.PlayerDao
import com.example.ulysse.dahiez.tp1.kisuije.database.dao.StarDao
import com.example.ulysse.dahiez.tp1.kisuije.database.entities.Competitor
import com.example.ulysse.dahiez.tp1.kisuije.database.entities.Game
import com.example.ulysse.dahiez.tp1.kisuije.database.entities.Player
import com.example.ulysse.dahiez.tp1.kisuije.database.entities.Star
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MyDatabase::class.java,
                    "my_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }

        private class MyDatabaseCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        // Pré-remplir la base de données ici
                        val starDao = database.starDao()
                        starDao.insert(Star(name = "Brad Pitt"))
                        starDao.insert(Star(name = "Angelina Jolie"))
                        starDao.insert(Star(name = "Leonardo DiCaprio"))
                        starDao.insert(Star(name = "Julia Roberts"))
                        starDao.insert(Star(name = "Meryl Streep"))
                        starDao.insert(Star(name = "Jean Dujardin"))
                        starDao.insert(Star(name = "Marion Cotillard"))
                        starDao.insert(Star(name = "Gérard Depardieu"))
                        starDao.insert(Star(name = "Omar Sy"))
                        starDao.insert(Star(name = "Audrey Tautou"))
                    }
                }
            }
        }
    }
}
