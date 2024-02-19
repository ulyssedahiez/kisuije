package com.example.ulysse.dahiez.tp1.kisuije
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.Spinner
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class PlayersRegisteryActivity : AppCompatActivity() {
    private lateinit var containerLayout: LinearLayout
    private var playerCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_players_registery)

        containerLayout = findViewById(R.id.containerLayout)
    }

    fun onAddPlayerButtonClick(view: View) {
        if (playerCount < 6) {  // Limit to adding 6 players, adjust as needed
            playerCount++

            val textView = TextView(this)
            textView.text = "Joueur $playerCount : "

            val spinner = Spinner(this)
            val playerNames = listOf("Joueur 1", "Joueur 2", "Joueur 3")
            val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, playerNames)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter

            containerLayout.addView(textView)
            containerLayout.addView(spinner)
        }
    }
}