package com.example.gamehw4

import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.content.SharedPreferences
import android.os.Bundle
import com.example.gamehw4.R
import android.content.Intent
import com.example.gamehw4.StartGame
import android.os.CountDownTimer
import android.view.View
import com.example.gamehw4.GameOver

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun startGame(view: View?) {
        // In startGame() method, create an Intent to launch StartGame Activity
        val intent = Intent(this@MainActivity, StartGame::class.java)
        startActivity(intent)
        // Finish MainActivity
        finish()
    }
}