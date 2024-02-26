package com.example.ulysse.dahiez.tp1.kisuije.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverter
import androidx.room.TypeConverters
import com.example.ulysse.dahiez.tp1.kisuije.database.dao.CompetitorDao
import com.example.ulysse.dahiez.tp1.kisuije.database.dao.GameDao
import com.example.ulysse.dahiez.tp1.kisuije.database.dao.PlayerDao
import com.example.ulysse.dahiez.tp1.kisuije.database.dao.RoundDao
import com.example.ulysse.dahiez.tp1.kisuije.database.dao.StarDao
import com.example.ulysse.dahiez.tp1.kisuije.database.entities.Competitor
import com.example.ulysse.dahiez.tp1.kisuije.database.entities.Game
import com.example.ulysse.dahiez.tp1.kisuije.database.entities.Player
import com.example.ulysse.dahiez.tp1.kisuije.database.entities.Round
import com.example.ulysse.dahiez.tp1.kisuije.database.entities.Star
import org.json.JSONArray
import org.json.JSONObject

@Database(entities = [Game::class, Player::class, Round::class, Competitor::class, Star::class], version = 1)
@TypeConverters(Converters::class)
abstract class MyDatabase : RoomDatabase() {

    abstract fun gameDao(): GameDao
    abstract fun playerDao(): PlayerDao
    abstract fun roundDao(): RoundDao
    abstract fun competitorDao(): CompetitorDao
    abstract fun starDao(): StarDao

    companion object {
        @Volatile
        private var INSTANCE: MyDatabase? = null

        fun getDatabase(context: Context): MyDatabase {
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
    }
}

class Converters {
    @TypeConverter
    fun fromPlayerList(playerList: List<Player>): String {
        val jsonArray = JSONArray()
        for (player in playerList) {
            val jsonObject = JSONObject()
            jsonObject.put("id", player.id_player)
            jsonObject.put("name", player.name)
            // Ajoutez d'autres champs si nécessaire
            jsonArray.put(jsonObject)
        }
        return jsonArray.toString()
    }

    @TypeConverter
    fun toPlayerList(playerListString: String): List<Player> {
        val playerList = mutableListOf<Player>()
        val jsonArray = JSONArray(playerListString)
        for (i in 0 until jsonArray.length()) {
            val jsonObject = jsonArray.getJSONObject(i)
            val id = jsonObject.getInt("id")
            val name = jsonObject.getString("name")
            // Ajoutez d'autres champs si nécessaire
            val player = Player(id, name, 0,0)
            playerList.add(player)
        }
        return playerList
    }
}
