package com.example.ulysse.dahiez.tp1.kisuije

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.ulysse.dahiez.tp1.kisuije.database.MyDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.Serializable

class winnerActivity : AppCompatActivity() {
    data class Player(val name: String, val assignedWord: String, var winner: Boolean) : Serializable
    val MyDataBase: MyDatabase by lazy {
        Room.databaseBuilder(
            applicationContext,
            MyDatabase::class.java,
            "MyDataBase.db"
        ).build()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_winner)

        // Récupérer le nom du joueur de l'intent
        val playerName = intent.getStringExtra("playerName")

        // Afficher le texte "Nom du joueur a gagné !"
        val winnerTextView = findViewById<TextView>(R.id.winnerTextView)
        winnerTextView.text = "$playerName a gagné !"

        // Trouver le bouton "Retour à la partie"
        val returnButton = findViewById<Button>(R.id.returnButton)
        // Ajouter un OnClickListener pour revenir à GameActivity
        returnButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
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
