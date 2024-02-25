package com.example.ulysse.dahiez.tp1.kisuije

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
    }

    fun onPlayerWinButtonClick() {
        val playerScoreTextView = findViewById<TextView>(R.id.playerScoreTextView)
        val playerScore = 1+1

        playerScoreTextView.text = "Le score est : $playerScore"
        playerScoreTextView.visibility = TextView.VISIBLE
    }
}