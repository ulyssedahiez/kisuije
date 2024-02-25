package com.example.ulysse.dahiez.tp1.kisuije

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class winnerActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_winner)

        // Récupérer le nom du joueur de l'intent
        val playerName = intent.getStringExtra("playerName")

        // Afficher le texte "Nom du joueur a gagné !"
        val winnerTextView = findViewById<TextView>(R.id.winnerTextView)
        winnerTextView.text = "$playerName a gagné !"

        // Trouver le bouton "Retour à la partie"
        val returnButton = findViewById<Button>(R.id.returnButton)
        // Ajouter un OnClickListener pour revenir à GameActivity
        returnButton.setOnClickListener {
            val intent = Intent(this, GameActivity::class.java)
            startActivity(intent)
        }
    }
}
