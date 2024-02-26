package com.example.ulysse.dahiez.tp1.kisuije

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import java.io.Serializable

class winnerActivity : AppCompatActivity() {
    data class Player(val name: String, val assignedWord: String, var winner: Boolean) : Serializable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_winner)

        // Récupérer le nom du joueur de l'intent
        val playerName = intent.getStringExtra("playerName")

        // Récupérer la liste des joueurs
        val playersList = intent.getSerializableExtra("playerList") as? MutableList<PlayerInfoActivity.Player>

        // Afficher le texte "Nom du joueur a gagné !"
        val winnerTextView = findViewById<TextView>(R.id.winnerTextView)
        winnerTextView.text = "$playerName a gagné !"

        // Trouver le bouton "Retour à la partie"
        val returnButton = findViewById<Button>(R.id.returnButton)
        // Ajouter un OnClickListener pour revenir à GameActivity
        returnButton.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)
            // Passez la liste des joueurs à l'activité suivante
            intent.putExtra("playerList", playersList as Serializable)
            startActivity(intent)
        }
    }
}
