package com.example.ulysse.dahiez.tp1.kisuije

import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class NewGameActivity : AppCompatActivity() {
    private lateinit var containerLayout: LinearLayout
    private var playerCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_game)

        containerLayout = findViewById(R.id.containerLayout)
    }

    fun onAddPlayerButtonClick(view: View) {
        if (playerCount < 6) {  // Limite à 6 joueurs, ajuste selon tes besoins
            playerCount++

            // Créer un nouveau LinearLayout horizontal pour chaque joueur
            val playerLayout = LinearLayout(this)
            playerLayout.orientation = LinearLayout.HORIZONTAL

            val textView = TextView(this)
            textView.text = "Joueur $playerCount : "

            val spinner = Spinner(this)
            // Configurer le spinner avec les noms des joueurs ou d'autres données
            val playerNames = listOf("Joueur 1", "Joueur 2", "Joueur 3")
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, playerNames)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter

            val btnRemovePlayer = Button(this)
            btnRemovePlayer.text = "Supprimer Joueur"
            btnRemovePlayer.setOnClickListener {
                onRemovePlayerButtonClick(playerLayout)
            }

            // Ajouter les éléments au LinearLayout du joueur
            playerLayout.addView(textView)
            playerLayout.addView(spinner)
            playerLayout.addView(btnRemovePlayer)

            // Ajouter le LinearLayout du joueur au conteneur principal
            containerLayout.addView(playerLayout)
        }
    }

    fun onRemovePlayerButtonClick(playerLayout: LinearLayout) {
        containerLayout.removeView(playerLayout)
        playerCount--
    }
}