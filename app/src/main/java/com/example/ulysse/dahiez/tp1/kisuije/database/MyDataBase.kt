package com.example.ulysse.dahiez.tp1.kisuije.database

import android.content.Context
import android.util.Log
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
import kotlinx.coroutines.GlobalScope
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
                ).addCallback(MyDatabaseCallback(GlobalScope)).build()
                INSTANCE = instance
                instance
            }
        }

        private class MyDatabaseCallback(private val scope: CoroutineScope) : RoomDatabase.Callback() {
            override fun onCreate(db: SupportSQLiteDatabase) {
                super.onCreate(db)
                Log.d("MyDatabaseCallback", "Database created. Inserting stars...")
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        val starDao = database.starDao()
                        starDao.insert(Star(name = "Brad Pitt"))
                        starDao.insert(Star(name = "Angelina Jolie"))

                        Log.d("MyDatabaseCallback", "Stars inserted successfully.")
                    }
                }
            }
        }
        }
    }



