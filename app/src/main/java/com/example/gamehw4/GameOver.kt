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

class GameOver : AppCompatActivity() {
    // Declare TextView object references for showing points and personal best.
    var tvPoints: TextView? = null
    var tvPersonalBest: TextView? = null

    // Declare a SharedPreferences object reference since we are going to store
    // the highest score or personal best using Android SharedPreferences class.
    var sharedPreferences: SharedPreferences? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_over)
        // Receive the extra data from Intent that is sent from StartGame Activity.
        val points = intent.extras!!.getInt("points")
        // Get the handles of the TextViews for points and personal best
        tvPoints = findViewById(R.id.tvPoints)
        tvPersonalBest = findViewById(R.id.tvPersonalBest)
        // Set tvPoints with the value of points
        tvPoints.setText("" + points)
        // Instantiate the SharedPreferences object reference
        sharedPreferences = getSharedPreferences("pref", 0)
        // Here pref is the name of the file and 0 for private mode.
        // To read values from SharedPreferences, we'll use getInt() method on
        // sharedPreferences object.
        var pointsSP = sharedPreferences.getInt("pointsSP", 0)
        // The first parameter of getInt() is the key.
        // The second parameter is the default value that is, value to return if this preference
        // does not exist.
        // So, this is how we created a key named "pointsSP" using SharedPreferences,
        // retrieved value from it and stored in another integer variable
        // also named "pointsSP".
        // Next, Create a SharedPreferences.Editor object by calling edit() method
        // on sharedPreferences object.
        val editor = sharedPreferences.edit()
        // Check, if points is greater than pointsSP
        if (points > pointsSP) {
            // If true, store points in pointsSP
            pointsSP = points
            // Put the value in editor with the key "pointsSP"
            editor.putInt("pointsSP", pointsSP)
            // This will overwrite existing value of the key "pointsSP".
            // To save the changes call commit() on editor.
            editor.commit()
        }
        // Set tvPersonalBest with the value of pointsSP
        tvPersonalBest.setText("" + pointsSP)
    }

    fun restart(view: View?) {
        // Create an Intent object to launch StartGame Activity
        val intent = Intent(this@GameOver, StartGame::class.java)
        startActivity(intent)
        // Finish GameOver Activity
        finish()
    }

    fun exit(view: View?) {
        // Call finish() method to finish GameOver Activity
        finish()
    }
}