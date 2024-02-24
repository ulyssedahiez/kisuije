package com.example.ulysse.dahiez.tp1.kisuije

import android.os.Bundle
import android.widget.Button
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
        val players = listOf("Joueur 1", "Joueur 2", "Joueur 3","Joueur 1", "Joueur 2", "Joueur 3","Joueur 1", "Joueur 2", "Joueur 3","Joueur 1", "Joueur 2", "Joueur 3","Joueur 1", "Joueur 2", "Joueur 3","Joueur 1", "Joueur 2", "Joueur 3","Joueur 1", "Joueur 2", "Joueur 3")

        // Créer et ajouter des LinearLayout avec un TextView et un bouton "Supprimer" pour chaque joueur
        for (player in players) {
            // Créer un LinearLayout pour contenir le TextView et le bouton
            val playerLayout = LinearLayout(this)
            playerLayout.orientation = LinearLayout.HORIZONTAL
            playerLayout.layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            playerLayout.setBackgroundResource(R.drawable.background_white_rounded_corners)
            playerLayout.setPadding(16, 16, 16, 16)

            // Créer le TextView pour afficher le nom du joueur
            val playerTextView = TextView(this)
            playerTextView.text = player
            playerTextView.setTextColor(resources.getColor(R.color.yellow))
            playerTextView.layoutParams = LinearLayout.LayoutParams(
                0,
                LinearLayout.LayoutParams.WRAP_CONTENT,
                1.0f
            )

            // Créer le bouton "Supprimer"
            val deleteButton = Button(this)
            deleteButton.text = "Supprimer"
            deleteButton.setTextColor(resources.getColor(android.R.color.white)) // Couleur du texte en blanc
            deleteButton.setBackgroundResource(R.drawable.button_background_yellow) // Appliquer le fond jaune avec border radius

            // Ajouter un écouteur de clic au bouton "Supprimer"
            deleteButton.setOnClickListener {
                println("L'élément $player a été supprimé")
            }

            // Ajouter le bouton "Supprimer" et le TextView au LinearLayout
            playerLayout.addView(playerTextView)
            playerLayout.addView(deleteButton)

            // Ajouter le LinearLayout à la ScrollView
            containerLayout.addView(playerLayout)

            // Ajouter une marge inférieure entre les éléments de la liste
            val layoutParams = playerLayout.layoutParams as LinearLayout.LayoutParams
            layoutParams.bottomMargin = resources.getDimensionPixelSize(R.dimen.item_margin_vertical)
        }

        // Gérer le clic sur le bouton de retour
        backButton.setOnClickListener {
            onBackPressed()
        }
    }
}
