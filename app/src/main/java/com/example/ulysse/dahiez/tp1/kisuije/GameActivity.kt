package com.example.ulysse.dahiez.tp1.kisuije

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import java.io.Serializable
import com.example.ulysse.dahiez.tp1.kisuije.BackButtonFragment

class GameActivity : AppCompatActivity() {
    private lateinit var containerLayout: LinearLayout
    private var currentRound: Int = 1
    private lateinit var roundsTextView: TextView

    data class Player(val name: String, val assignedWord: String, val winner:Boolean) : Serializable

    // Liste des personnalités françaises
    private val lifeObjectsList: List<String> = listOf(
        "Marion Cotillard", "Omar Sy", "Jean Dujardin", "Sophie Marceau", "Vincent Cassel",
        "Audrey Tautou", "Guillaume Canet", "Juliette Binoche", "Michel Platini", "Zinedine Zidane",
        "Catherine Deneuve", "Johnny Hallyday", "Brigitte Bardot", "Serge Gainsbourg", "Isabelle Adjani",
        "Gérard Depardieu", "Charlotte Gainsbourg", "Yannick Noah", "Vanessa Paradis", "Romain Duris",
        "Carla Bruni", "Alain Delon", "Mylène Farmer", "Florence Foresti", "Jean-Paul Belmondo",
        "Nolwenn Leroy", "Olivier Giroud", "Renaud", "Laetitia Casta", "David Guetta"
    )

    private val playersList = mutableListOf<Player>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        containerLayout = findViewById(R.id.containerLayout)
        roundsTextView = findViewById(R.id.roundsTextView)

        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.fragmentContainer, BackButtonFragment())
        fragmentTransaction.commit()

        // Récupérer la liste des noms de joueurs depuis l'intent
        val playerNames = intent.getStringArrayListExtra("playerNames")

        if (playerNames != null) {
            for (playerName in playerNames) {
                // Assigner un mot aléatoire à chaque joueur
                val assignedWord = lifeObjectsList.shuffled().firstOrNull() ?: "Mot par défaut"
                // Créer un joueur avec le nom et le mot attribué
                val player = Player(playerName, assignedWord, false)
                playersList.add(player)
                // Créer le bouton du joueur et l'ajouter à la vue
                val playerButton = createPlayerButton(player)
                containerLayout.addView(playerButton)
            }
        }
        // Mettre à jour le TextView des tours
        updateRoundsTextView()
    }

    private fun createPlayerButton(player: Player): Button {
        val playerButton = Button(this)
        playerButton.text = player.name
        playerButton.setOnClickListener { navigateToPlayerInfo(player) }

        playerButton.gravity = Gravity.CENTER

        playerButton.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

        val params = playerButton.layoutParams as LinearLayout.LayoutParams
        params.setMargins(20, 20, 20, 20)
        playerButton.backgroundTintList = ContextCompat.getColorStateList(this, R.color.white)
        val colorResId = R.color.yellow
        val textColor = ContextCompat.getColor(this, colorResId)
        playerButton.setTextColor(textColor)
        return playerButton
    }

    private fun navigateToPlayerInfo(player: Player) {
        // Lancer une nouvelle activité pour afficher les informations du joueur
        Log.d("GameActivity", "playerName : ${player.name}")
        val intent = Intent(this, PlayerInfoActivity::class.java)
        intent.putExtra("player", player.name)
        intent.putExtra("playerObject", player.assignedWord)
        intent.putExtra("playerWinner", player.winner)
        intent.putExtra("playerList", playersList as Serializable)
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
