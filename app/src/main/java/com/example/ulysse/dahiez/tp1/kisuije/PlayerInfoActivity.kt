package com.example.ulysse.dahiez.tp1.kisuije

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.io.Serializable

class PlayerInfoActivity : AppCompatActivity() {
    data class Player(val name: String, val assignedWord: String) : Serializable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_info)

        // Récupérer les informations du joueur depuis l'intent
        val playerName = intent.getStringExtra("playerName")
        val assignedWord = intent.getStringExtra("playerObject")

        // Afficher les informations du joueur
        val playerNameTextView = findViewById<TextView>(R.id.playerNameTextView)
        val playerWordTextView = findViewById<TextView>(R.id.playerWordTextView)

        playerNameTextView.text = "Info du joueur : $playerName"
        playerWordTextView.text = assignedWord

        // Trouver le bouton "Le joueur a gagné"
        val playerWinButton = findViewById<Button>(R.id.playerWinButton)
        // Ajouter un OnClickListener au bouton
        playerWinButton.setOnClickListener {
            // Créer une intention pour ouvrir WinnerActivity
            val intent = Intent(this, winnerActivity::class.java)
            // Ajouter le nom du joueur à l'intention
            intent.putExtra("playerName", playerName)
            // Démarrer l'activité WinnerActivity
            startActivity(intent)
        }

        // Bouton de retour
        val backButton = findViewById<Button>(R.id.backButton)
        backButton.setOnClickListener {
            onBackPressed()
        }
    }
}
