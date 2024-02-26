package com.example.ulysse.dahiez.tp1.kisuije

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import java.io.Serializable

class PlayerInfoActivity : AppCompatActivity() {
    data class Player(val name: String, val assignedWord: String, var winner: Boolean) : Serializable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_player_info)

        // Récupérer les informations du joueur depuis l'intent
        val playerName = intent.getStringExtra("playerName")
        val assignedWord = intent.getStringExtra("playerObject")
        val winnerPlayer = intent.getBooleanExtra("winnerPlayer", false)

        // Récupérer la liste des joueurs
        val playersList = intent.getSerializableExtra("playerList") as? MutableList<Player>

        // Afficher les informations du joueur
        val playerNameTextView = findViewById<TextView>(R.id.playerNameTextView)
        val playerWordTextView = findViewById<TextView>(R.id.playerWordTextView)

        playerNameTextView.text = "Info du joueur : $playerName"
        playerWordTextView.text = assignedWord

        if (winnerPlayer) {
            val winnerTextView = findViewById<TextView>(R.id.winnerTextView)
            winnerTextView.visibility = TextView.VISIBLE


        } else {
            val playerWinButton = findViewById<Button>(R.id.playerWinButton)

            // Mettre à jour le statut du gagnant dans la liste des joueurs
            val player = playersList?.find { it.name == playerName }
            player?.winner = true

            playerWinButton.setOnClickListener {
                // Créer une intention pour ouvrir WinnerActivity
                val intent = Intent(this, winnerActivity::class.java)

                // Passez la liste mise à jour à l'activité suivante
                intent.putExtra("playerList", playersList as Serializable)

                // Ajouter le nom du joueur à l'intention
                intent.putExtra("playerName", playerName)

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