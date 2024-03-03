package com.example.ulysse.dahiez.tp1.kisuije

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.ulysse.dahiez.tp1.kisuije.database.MyDatabase
import com.example.ulysse.dahiez.tp1.kisuije.database.entities.Competitor
import com.example.ulysse.dahiez.tp1.kisuije.database.entities.Player
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import android.graphics.Typeface
import kotlinx.coroutines.android.awaitFrame

class NewGameActivity : AppCompatActivity() {
    private lateinit var containerLayout: LinearLayout
    private var playerCount = 0
    private val playerNameEditTextList = mutableListOf<EditText>()
    private val allPlayerNames = mutableListOf<String>() // Liste pour stocker les noms des joueurs existants

    private val MyDataBase: MyDatabase by lazy {
        Room.databaseBuilder(
            applicationContext,
            com.example.ulysse.dahiez.tp1.kisuije.database.MyDatabase::class.java,
            "MyDataBase.db"
        ).build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_game)
        containerLayout = findViewById(R.id.containerLayout)

        // Récupérer les noms des joueurs existants lors de la création de l'activité
        fetchAllPlayerNames()
    }

    private fun fetchAllPlayerNames() {
        lifecycleScope.launch(Dispatchers.IO) {
            val maBaseDeDonnees = MyDataBase
            val utilisateurDao = maBaseDeDonnees.playerDao()
            allPlayerNames.addAll(utilisateurDao.getAllPlayerName() as List<String>)
        }
    }

    //Max 6 joueurs
    fun onAddPlayerButtonClick(view: View) {
        if (playerCount < 6) {
            playerCount++
            val playerLayout = createPlayerLayout()
            containerLayout.addView(playerLayout, 1)
            // Mettre à jour la liste des noms de joueurs existants lors de l'ajout d'un nouveau joueur
            updateAllPlayerNames()
        }
    }

    private fun updateAllPlayerNames() {
        lifecycleScope.launch(Dispatchers.IO) {
            val maBaseDeDonnees = MyDataBase
            val utilisateurDao = maBaseDeDonnees.playerDao()
            allPlayerNames.clear()
            allPlayerNames.addAll(utilisateurDao.getAllPlayerName() as List<String>)
        }
    }

    private fun createPlayerLayout(): LinearLayout {
        val playerLayout = LinearLayout(this)
        playerLayout.orientation = LinearLayout.VERTICAL

        val nameLayout = createNameLayout(playerCount)
        playerLayout.addView(nameLayout)

        val buttonsLayout = createButtonsLayout(playerCount)
        playerLayout.addView(buttonsLayout)

        return playerLayout
    }


    private fun createNameLayout(playerIndex: Int): LinearLayout {
        val nameLayout = LinearLayout(this)
        nameLayout.orientation = LinearLayout.HORIZONTAL

        val textView = createTextView(playerIndex)
        val playerNameEditText = createEditText(playerIndex)

        nameLayout.addView(textView)
        nameLayout.addView(playerNameEditText)
        nameLayout.gravity = Gravity.CENTER

        // Ajouter l'EditText à la liste
        playerNameEditTextList.add(playerNameEditText)

        return nameLayout
    }

    private fun createEditText(playerIndex: Int): EditText {
        val playerNameEditText = EditText(this)
        playerNameEditText.hint = "Nom du joueur"
        val colorResId = R.color.yellow
        val textColor = ContextCompat.getColor(this, colorResId)
        playerNameEditText.setTextColor(textColor)
        playerNameEditText.setHintTextColor(textColor)

        // Attribuer un ID unique en fonction de l'index du joueur
        playerNameEditText.id = View.generateViewId() + playerIndex
        Log.d("playerNameEditText.id",playerNameEditText.id.toString())

        return playerNameEditText
    }

    private fun createButtonsLayout(playerIndex: Int): LinearLayout {
        val buttonsLayout = LinearLayout(this)
        buttonsLayout.orientation = LinearLayout.HORIZONTAL
        buttonsLayout.gravity = Gravity.CENTER

        val btnImportPlayer = createButton("Importer") {
            showImportPlayerDialog(playerIndex)
        }
        val btnRemovePlayer = createButton("Supprimer") {
            onRemovePlayerButtonClick(buttonsLayout.parent as LinearLayout)
        }

        val buttonWidthInDp = 400
        val buttonLayoutParams = LinearLayout.LayoutParams(
            buttonWidthInDp,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )

        btnImportPlayer.layoutParams = buttonLayoutParams
        btnRemovePlayer.layoutParams = buttonLayoutParams

        buttonsLayout.addView(btnImportPlayer)
        buttonsLayout.addView(btnRemovePlayer)

        return buttonsLayout
    }

    private fun createTextView(playerIndex: Int): TextView {
        val textView = TextView(this)
        textView.text = "Joueur $playerIndex : "
        textView.textSize = 18f
        val colorResId = R.color.yellow
        val textColor = ContextCompat.getColor(this, colorResId)
        textView.setTextColor(textColor)
        textView.setTypeface(null, Typeface.BOLD)
        return textView
    }

    private fun createButton(text: String, onClickListener: () -> Unit): Button {
        val button = Button(this)
        button.text = text
        button.backgroundTintList = ContextCompat.getColorStateList(this, R.color.white)
        val colorResId = R.color.yellow
        val textColor = ContextCompat.getColor(this, colorResId)
        button.setTextColor(textColor)
        button.setOnClickListener { onClickListener.invoke() }
        return button
    }

    private fun onRemovePlayerButtonClick(playerLayout: LinearLayout) {
        // Récupérer l'index du joueur à partir de son layout parent
        val playerIndex = containerLayout.indexOfChild(playerLayout)
        // Supprimer le layout du joueur
        containerLayout.removeViewAt(playerIndex)
        // Supprimer l'EditText correspondant de la liste
        playerNameEditTextList.removeAt(playerIndex)
        // Mettre à jour les indices des EditText dans la liste
        for (i in playerIndex until playerNameEditTextList.size) {
            val editText = playerNameEditTextList[i]
            editText.id -= 1
        }
        // Mettre à jour le nombre de joueurs
        playerCount--
    }


    private fun showImportPlayerDialog(playerIndex: Int) {
        val maBaseDeDonnees = MyDataBase
        val utilisateurDao = maBaseDeDonnees.playerDao()

        lifecycleScope.launch(Dispatchers.IO) {
            // Mettre à jour la liste des noms de joueurs existants avant de récupérer les noms des joueurs importés
            val allPlayerNames = utilisateurDao.getAllPlayerName() as List<String>

            withContext(Dispatchers.Main) {
                val builder = AlertDialog.Builder(this@NewGameActivity)
                builder.setTitle("Choisir un joueur existant")

                val playerNamesArray = allPlayerNames.toTypedArray()
                builder.setItems(playerNamesArray) { _, which ->
                    val playerNameEditText = playerNameEditTextList[playerIndex - 1]
                    playerNameEditText.setText(playerNamesArray[which])
                }

                builder.setNegativeButton("Annuler") { dialog, _ ->
                    dialog.dismiss()
                }
                builder.show()
            }
        }
    }

    fun onvalidateButtonClick(view: View) {
        // Créer une liste pour stocker les noms des joueurs
        val playerNames = ArrayList<String>()

        val maBaseDeDonnees = MyDataBase

        val utilisateurDao = maBaseDeDonnees.playerDao()
        val competitorDao = maBaseDeDonnees.competitorDao()

        lifecycleScope.launch(Dispatchers.IO) {
            // Ajouter les joueurs créés à la liste des noms des joueurs
            for (i in 0 until playerCount) {
                val playerNameEditText = playerNameEditTextList[i]
                val playerName = playerNameEditText.text.toString()

                playerNames.add(playerName)
            }

            // Ajouter les joueurs dans la base de données s'ils n'existent pas déjà
            for (playerName in playerNames) {
                if (!allPlayerNames.contains(playerName)) {
                    val player = Player(name = playerName, nb_game = 0, total_point = 0)
                    utilisateurDao.insert(player)
                }
                // Si le joueur existe, récupérer son ID
                val player = utilisateurDao.getPlayerByName(playerName)
                val competitor = Competitor(id_player = player!!.id_player.toInt(), id_star = 0, nb_point = player!!.total_point, id_game = 0)
                competitorDao.insert(competitor)
            }

            withContext(Dispatchers.Main) {
                awaitFrame()
            }
        }

        // Créer une intent pour ouvrir GameActivity
        if (view.id == R.id.btnValidate) {
            val pageGame = Intent(this, GameActivity::class.java)
            pageGame.putStringArrayListExtra("playerNames", ArrayList(playerNames))
            startActivity(pageGame)
        }
    }

}
