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


class NewGameActivity : AppCompatActivity() {
    private lateinit var containerLayout: LinearLayout
    private var playerCount = 0
    private val playerNameEditTextList = mutableListOf<EditText>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_game)
        containerLayout = findViewById(R.id.containerLayout)
    }

    //Max 6 joueurs
    fun onAddPlayerButtonClick(view: View) {
        if (playerCount < 6) {
            playerCount++
            val playerLayout = createPlayerLayout()
            containerLayout.addView(playerLayout, 1)
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
        textView.setTypeface(null, 1)
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
        containerLayout.removeView(playerLayout)
        playerCount--
    }

    private fun showImportPlayerDialog(playerIndex: Int) {
        val playerNames = arrayOf("Joueur 1", "Joueur 2", "Joueur 3")
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Choisir un joueur existant")

        builder.setItems(playerNames) { _, which ->
            val selectedPlayer = playerNames[which]
            playerNameEditTextList[playerIndex - 1].setText(selectedPlayer)
        }

        builder.setNegativeButton("Annuler") { dialog, _ ->
            dialog.dismiss()
        }
        builder.show()
    }

    fun onvalidateButtonClick(view: View) {
        // Créer une liste pour stocker les noms des joueurs
        val playerNames = ArrayList<String>()

        for (i in 0 until playerCount) {
            val playerNameEditText = playerNameEditTextList[i]
            val playerName = playerNameEditText.text.toString()
            playerNames.add(playerName)
        }

        // Utiliser le paramètre 'view' pour obtenir la référence au bouton qui a été cliqué
        if (view.id == R.id.btnValidate) {
            val pageGame = Intent(this, GameActivity::class.java)
            pageGame.putStringArrayListExtra("playerNames", ArrayList(playerNames))
            startActivity(pageGame)
        }
    }
}