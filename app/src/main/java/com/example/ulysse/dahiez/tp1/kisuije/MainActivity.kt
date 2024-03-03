package com.example.ulysse.dahiez.tp1.kisuije

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.ulysse.dahiez.tp1.kisuije.database.MyDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    val MyDataBase: MyDatabase by lazy {
        Room.databaseBuilder(
            applicationContext,
            MyDatabase::class.java,
            "MyDataBase.db"
        ).build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonPlay: Button = findViewById(R.id.buttonPlay);
        val imgListPlayer = findViewById<ImageView>(R.id.buttonListPlayer);

        imgListPlayer.setOnClickListener {
            val intent = Intent(this, PlayersList::class.java)
            startActivity(intent)
        }
        buttonPlay.setOnClickListener {
            val intent = Intent(this, NewGameActivity::class.java)
            //Effacer la table competitor
            val maBaseDeDonnees = MyDataBase
            val competitorDao = maBaseDeDonnees.competitorDao()

            lifecycleScope.launch(Dispatchers.IO) {
                competitorDao.deleteAllCompetitors()
            }

            startActivity(intent)
        }

    }
}