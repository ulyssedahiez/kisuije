package com.example.ulysse.dahiez.tp1.kisuije

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import java.io.Serializable

class PlayerInfoActivity : AppCompatActivity() {
    data class Player(val name: String, val assignedWord: String) : Serializable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_info)

        //private val playersList = mutableListOf<Player>()

        // Récupérer les informations du joueur depuis l'intent
        val playerName = intent.getStringExtra("player")
        val assignedWord = intent.getStringExtra("playerObject")
        val winnerPlayer = intent.getBooleanExtra("winnerPlayer",false)

        val playersList = intent.getSerializableExtra("playerList")

        // Afficher les informations du joueur
        val playerNameTextView = findViewById<TextView>(R.id.playerNameTextView)
        val playerWordTextView = findViewById<TextView>(R.id.playerWordTextView)

        playerNameTextView.text = "Info du joueur : $playerName"
        playerWordTextView.text = assignedWord

        if (winnerPlayer){
            val winnerTextView = findViewById<TextView>(R.id.winnerTextView)
            winnerTextView.visibility = TextView.VISIBLE
        }
        else {
            val playerWinButton = findViewById<Button>(R.id.playerWinButton)
            Log.d("PlayerInfoActivity", "playerName : $playerName")
            playerWinButton.setOnClickListener {
                // Créer une intention pour ouvrir WinnerActivity
                val intent = Intent(this, winnerActivity::class.java)
                // Ajouter le nom du joueur à l'intention
                intent.putExtra("playerName", playerName)
                intent.putExtra("playerList", playersList)
                // Démarrer l'activité WinnerActivity
                startActivity(intent)
            }
        }

        // Bouton de retour
        val backButton = findViewById<Button>(R.id.backButton)
        backButton.setOnClickListener {
            onBackPressed()
        }
    }
}
