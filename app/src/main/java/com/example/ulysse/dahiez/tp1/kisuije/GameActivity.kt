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
import androidx.lifecycle.lifecycleScope
import com.example.ulysse.dahiez.tp1.kisuije.database.MyDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.Serializable

class GameActivity : AppCompatActivity() {
    private lateinit var containerLayout: LinearLayout
    private var currentRound: Int = 1
    private lateinit var roundsTextView: TextView

    data class Player(val name: String, val assignedStarName: String, val winner: Boolean) : Serializable

    // Liste des noms de stars récupérés depuis la base de données
    private val starNamesList: MutableList<String> = mutableListOf()

    private val playersList = mutableListOf<Player>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loadStarNamesFromDatabase()
        setContentView(R.layout.activity_game)

        containerLayout = findViewById(R.id.containerLayout)
        roundsTextView = findViewById(R.id.roundsTextView)

        // Récupérer la liste des noms de joueurs depuis l'intent
        val playerNames = intent.getStringArrayListExtra("playerNames")

        if (playerNames != null) {
            // Charger les noms de stars depuis la base de données


            for (playerName in playerNames) {
                // Assigner un nom de star aléatoire à chaque joueur

                val assignedStarName = assignRandomStarName()
                Log.d("GameActivity","assignedStarName : "+assignedStarName )
                // Créer un joueur avec le nom et le nom de star attribué
                val player = Player(playerName, assignedStarName, false)
                playersList.add(player)
                // Créer le bouton du joueur et l'ajouter à la vue
                val playerButton = createPlayerButton(player)
                containerLayout.addView(playerButton)
            }
        }
        // Mettre à jour le TextView des tours
        updateRoundsTextView()
    }

    // Fonction pour charger les noms de stars depuis la base de données
    private fun loadStarNamesFromDatabase() {
        val starDao = MyDatabase.getMyDatabase(this).starDao()
        lifecycleScope.launch(Dispatchers.IO) {
            val stars = starDao.allStars()
            val starNames = stars?.mapNotNull { it?.name }
            Log.d("GameActivity", "Liste des noms de stars : $starNames")
            starNames?.let {
                starNamesList.addAll(it)

            }
        }
    }

    // Fonction pour attribuer un nom de star aléatoire à un joueur
    private fun assignRandomStarName(): String {
        Log.d("GameActivity", "in AssignedRandomStarName :: "+starNamesList)
        return starNamesList.shuffled().firstOrNull() ?: "Nom par défaut"
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
        intent.putExtra("playerObject", player.assignedStarName)
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
