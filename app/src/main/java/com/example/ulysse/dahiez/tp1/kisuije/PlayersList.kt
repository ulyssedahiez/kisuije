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
        val players = listOf("Joueur 1", "Joueur 2", "Joueur 3","Joueur 1", "Joueur 2", "Joueur 3","Joueur 1", "Joueur 2", "Joueur 3","Joueur 1", "Joueur 2", "Joueur 3","Joueur 1", "Joueur 2", "Joueur 3","Joueur 1", "Joueur 2", "Joueur 3","Joueur 1", "Joueur 2", "Joueur 3","Joueur 1", "Joueur 2", "Joueur 3","Joueur 1", "Joueur 2", "Joueur 3")

        // Créer et ajouter des TextView pour chaque joueur
        for (player in players) {
            val playerTextView = TextView(this)
            playerTextView.text = player
            playerTextView.setBackgroundResource(R.drawable.background_white_rounded_corners)
            playerTextView.setTextColor(resources.getColor(R.color.yellow))
            playerTextView.setPadding(16, 16, 16, 16)

            // Définir les marges pour créer de l'espace entre les éléments
            val layoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
            layoutParams.setMargins(
                resources.getDimensionPixelSize(R.dimen.item_margin_horizontal),
                0,
                resources.getDimensionPixelSize(R.dimen.item_margin_horizontal),
                resources.getDimensionPixelSize(R.dimen.item_margin_vertical)
            )
            playerTextView.layoutParams = layoutParams

            containerLayout.addView(playerTextView)
        }

        // Gérer le clic sur le bouton de retour
        backButton.setOnClickListener {
            onBackPressed()
        }
    }
}
