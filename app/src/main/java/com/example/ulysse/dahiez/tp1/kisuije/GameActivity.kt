package com.example.ulysse.dahiez.tp1.kisuije

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class GameActivity : AppCompatActivity() {
    private lateinit var containerLayout: LinearLayout
    private var currentRound: Int = 1
    private lateinit var roundsTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        containerLayout = findViewById(R.id.containerLayout)
        roundsTextView = findViewById(R.id.roundsTextView)

        // Récupérer la liste des noms de joueurs depuis l'intent
        val playerNames = intent.getStringArrayListExtra("playerNames")

        if (playerNames != null) {
            for (playerName in playerNames) {
                Log.d("GameActivity", "Nom du joueur : $playerName")
                val playerButton = Button(this)
                playerButton.text = playerName
                playerButton.setOnClickListener { navigateToPlayerInfo(playerName) }

                containerLayout.addView(playerButton)
            }
        }

        // Mettre à jour le TextView des tours
        updateRoundsTextView()
    }

    private fun navigateToPlayerInfo(playerName: String) {
        // Lancer une nouvelle activité pour afficher les informations du joueur
        val intent = Intent(this, PlayerInfoActivity::class.java)
        intent.putExtra("playerName", playerName)
        startActivity(intent)
    }

    private fun updateRoundsTextView() {
        roundsTextView.text = "Tour : $currentRound"
    }

    fun onNextRoundButtonClick(view: View) {
        currentRound++
        updateRoundsTextView()
    }
}
