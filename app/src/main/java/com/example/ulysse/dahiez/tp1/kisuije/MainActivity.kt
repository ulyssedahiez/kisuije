package com.example.ulysse.dahiez.tp1.kisuije

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonPlay : Button = findViewById(R.id.buttonPlay)

        buttonPlay.setOnClickListener{
            val intent = Intent(this,NewGameActivity::class.java)
            startActivity(intent)
        }

    }
}