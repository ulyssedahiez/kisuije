package com.example.ulysse.dahiez.tp1.kisuije

import android.os.Bundle
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.new_game)
        containerLayout = findViewById(R.id.containerLayout)
    }

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

        val nameLayout = createNameLayout()
        playerLayout.addView(nameLayout)

        val buttonsLayout = createButtonsLayout(nameLayout.getChildAt(1) as EditText)
        playerLayout.addView(buttonsLayout)

        return playerLayout
    }

    private fun createNameLayout(): LinearLayout {
        val nameLayout = LinearLayout(this)
        nameLayout.orientation = LinearLayout.HORIZONTAL

        val textView = createTextView()
        val playerNameEditText = createEditText()

        nameLayout.addView(textView)
        nameLayout.addView(playerNameEditText)
        nameLayout.gravity = Gravity.CENTER

        return nameLayout
    }

    private fun createButtonsLayout(playerNameEditText: EditText): LinearLayout {
        val buttonsLayout = LinearLayout(this)
        buttonsLayout.orientation = LinearLayout.HORIZONTAL
        buttonsLayout.gravity = Gravity.CENTER

        val btnImportPlayer = createButton("Importer") {
            showImportPlayerDialog(playerNameEditText)
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

    private fun createTextView(): TextView {
        val textView = TextView(this)
        textView.text = "Joueur $playerCount : "
        textView.textSize = 18f
        val colorResId = R.color.yellow
        val textColor = ContextCompat.getColor(this, colorResId)
        textView.setTextColor(textColor)
        textView.setTypeface(null, 1)
        return textView
    }

    private fun createEditText(): EditText {
        val playerNameEditText = EditText(this)
        playerNameEditText.hint = "Nom du joueur"
        val colorResId = R.color.yellow
        val textColor = ContextCompat.getColor(this, colorResId)
        playerNameEditText.setTextColor(textColor)
        playerNameEditText.setHintTextColor(textColor)
        return playerNameEditText
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

    private fun showImportPlayerDialog(playerNameEditText: EditText) {
        val playerNames = arrayOf("Joueur 1", "Joueur 2", "Joueur 3")
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Choisir un joueur existant")

        builder.setItems(playerNames) { _, which ->
            val selectedPlayer = playerNames[which]
            playerNameEditText.setText(selectedPlayer)
        }

        builder.setNegativeButton("Annuler") { dialog, _ ->
            dialog.dismiss()
        }
        builder.show()
    }
}