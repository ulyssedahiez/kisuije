package com.example.ulysse.dahiez.tp1.kisuije

import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class PlayersList : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_players_list)

        val containerLayout = findViewById<LinearLayout>(R.id.scrollViewLinearLayout)
        val backButton = findViewById<Button>(R.id.button_back)

        // Liste de joueurs
        val players = listOf("Joueur 1", "Joueur 2", "Joueur 3")

        // Créer et ajouter des TextView pour chaque joueur
        for (player in players) {
            val playerLayout = LinearLayout(this)
            playerLayout.orientation = LinearLayout.HORIZONTAL

            val playerTextView = TextView(this)
            playerTextView.text = player
            playerTextView.setBackgroundResource(R.drawable.background_white_rounded_corners)
            playerTextView.setTextColor(resources.getColor(R.color.yellow))
            playerTextView.setPadding(16, 16, 16, 16)

            // ImageButton pour supprimer un joueur
            val deleteButton = ImageButton(this)
            deleteButton.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            deleteButton.setImageResource(R.drawable.garbage)
            deleteButton.contentDescription = "Supprimer"

            // Ajouter les vues à playerLayout
            playerLayout.addView(playerTextView)
            playerLayout.addView(deleteButton)

            // Ajouter playerLayout à scrollViewLinearLayout
            containerLayout.addView(playerLayout)
        }

        // Gérer le clic sur le bouton de retour
        backButton.setOnClickListener {
            onBackPressed()
        }
    }
}
