package com.example.gamehw4

import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import android.content.SharedPreferences
import android.os.Bundle
import com.example.gamehw4.R
import android.content.Intent
import android.graphics.Color
import com.example.gamehw4.StartGame
import android.os.CountDownTimer
import android.view.View
import android.widget.Button
import android.widget.ImageView
import com.example.gamehw4.GameOver
import java.util.*

class StartGame : AppCompatActivity() {
    // Declare a TextView for showing Timer
    var tvTimer: TextView? = null

    // A TextView for showing Result
    var tvResult: TextView? = null

    // An ImageView for showing the image in question
    var ivShowImage: ImageView? = null

    // Instantiate a HashMap to store technology names and corresponding image resource ids
    var map = HashMap<String, Int>()

    // An ArrayList for storing logo names
    var logoList = ArrayList<String?>()

    // Declare an index variable.
    var index = 0

    // Declare four button object references for displaying four options
    var btn1: Button? = null
    var btn2: Button? = null
    var btn3: Button? = null
    var btn4: Button? = null

    // A TextView for displaying points
    var tvPoints: TextView? = null

    // An integer variable to store points
    var points = 0

    // A CountDownTimer object reference
    var countDownTimer: CountDownTimer? = null

    // And a long integer to store the time limit for each question to be used
    // with the CountDownTimer.
    var millisUntilFinished: Long = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_start)
        // Set the content view with a layout file.
        tvTimer = findViewById(R.id.tvTimer)
        // Get the handles for the Views
        tvResult = findViewById(R.id.tvResult)
        ivShowImage = findViewById(R.id.ivShowImage)
        btn1 = findViewById(R.id.btn1)
        btn2 = findViewById(R.id.btn2)
        btn3 = findViewById(R.id.btn3)
        btn4 = findViewById(R.id.btn4)
        tvPoints = findViewById(R.id.tvPoints)
        // Initialize index with 0
        index = 0
        // Populate logoList with all the technology names
        logoList.add("Bootstrap")
        logoList.add("C")
        logoList.add("CodeIgniter")
        logoList.add("C++")
        logoList.add("C#")
        logoList.add("Css3")
        logoList.add("GitHub")
        logoList.add("Html5")
        logoList.add("Java")
        logoList.add("jQuery")
        logoList.add("MySQL")
        logoList.add("Nodejs")
        logoList.add("Php")
        logoList.add("Python")
        logoList.add("Wordpress")
        logoList.add("Android")
        // Shuffle logoList so that each time we open the app it starts with
        // a random question
        Collections.shuffle(logoList)
        // Initialize millisUntilFinished. Set 10 thousand milliseconds or
        // 10 seconds time limit for each question (or as per your need).
        millisUntilFinished = 10000
        // Initialize points to 0
        points = 0
        // Call startGame() method that is responsible for starting the quiz.
        startGame()
    }

    private fun startGame() {
        // Initialize millisUntilFinished with 10 seconds.
        millisUntilFinished = 10000
        // Set the TextView for Timer.
        tvTimer!!.text = "" + millisUntilFinished / 1000 + "s"
        // Set the TextView for points.
        tvPoints!!.text = points.toString() + " / " + logoList.size
        // To generate a question, call generateQuestions() method and pass
        // index as parameter.
        generateQuestions(index)
        // Instantiate the countDownTimer object.
        countDownTimer = object : CountDownTimer(millisUntilFinished, 1000) {
            // In our case, onTick() callback method is fired on regular intervals of
            // 1000 milliseconds or 1 second and onFinish() callback method is fired
            // when the timer finishes.
            override fun onTick(millisUntilFinished: Long) {
                // Update tvTimer every 1 second to show the number of seconds remaining.
                tvTimer!!.text = "" + millisUntilFinished / 1000 + "s"
            }

            override fun onFinish() {
                // Increment index by 1 so that the next question can be presented
                // automatically when the user is unable to select their answer.
                index++
                // When timer is finished check if all questions are being asked.
                if (index > logoList.size - 1) {
                    // If true, hide the ImageView and Buttons.
                    ivShowImage!!.visibility = View.GONE
                    btn1!!.visibility = View.GONE
                    btn2!!.visibility = View.GONE
                    btn3!!.visibility = View.GONE
                    btn4!!.visibility = View.GONE
                    // Go to GameOver screen with points using an Intent
                    val intent = Intent(this@StartGame, GameOver::class.java)
                    intent.putExtra("points", points)
                    startActivity(intent)
                    // Finish StartGame Activity
                    finish()
                } else {
                    // In the else part, that is, if all questions are not being asked,
                    // initialize countDownTimer with null and call startGame() again.
                    // And this is the case, when no answer is selected before the
                    // time limit is over. So, the player will be presented with the
                    // next question and no points will be granted. If you want
                    // you can also decrement the points here for skipping a question
                    // and that'll make the quiz a bit harder.
                    countDownTimer = null
                    startGame()
                }
            }
        }.start() // Call start() method to start the timer.
    }

    private fun generateQuestions(index: Int) {
        // Clone logoList to a new ArrayList called logoListTemp.
        val logoList = logoList.clone() as ArrayList<String>
        // Get the correct answer for the current question from logoList using index.
        val correctAnswer = logoList[index]
        var logoListTemp: List<*>
        logoListTemp.remove(correctAnswer)
        Collections.shuffle(logoListTemp)
        // Create a temporary ArrayList for storing three non-repeated random answers
        // from logoListTemp and one correct answer.
        val newList = ArrayList<String?>()
        // Get first three elements from logoListTemp and add into newList.
        newList.add(logoListTemp[0])
        newList.add(logoListTemp[1])
        newList.add(logoListTemp[2])
        // Also add the correct answer into newList
        newList.add(correctAnswer)
        // Shuffle newList so that the correct answer can be placed in one of the four
        // buttons, randomly.
        Collections.shuffle(newList)
        btn1!!.text = newList[0]
        btn2!!.text = newList[1]
        btn3!!.text = newList[2]
        btn4!!.text = newList[3]
        // Also, set the ImageView with current image from map
        ivShowImage!!.setImageResource(map[logoList[index]]!!)
    }

    fun nextQuestion(view: View?) {
        // This method is called because the user has tapped the Next button,
        // so, set the background color of all the buttons to the color that we used in start_game.xml.
        btn1!!.setBackgroundColor(Color.parseColor("#2196f3"))
        btn2!!.setBackgroundColor(Color.parseColor("#2196f3"))
        btn3!!.setBackgroundColor(Color.parseColor("#2196f3"))
        btn4!!.setBackgroundColor(Color.parseColor("#2196f3"))
        // Enable the buttons
        btn1!!.isEnabled = true
        btn2!!.isEnabled = true
        btn3!!.isEnabled = true
        btn4!!.isEnabled = true
        // Cancel the countDownTimer
        countDownTimer!!.cancel()
        index++
        // Check if all questions have been asked.
        if (index > logoList.size - 1) {
            // If true, hide the ImageView and Buttons.
            ivShowImage!!.visibility = View.GONE
            btn1!!.visibility = View.GONE
            btn2!!.visibility = View.GONE
            btn3!!.visibility = View.GONE
            btn4!!.visibility = View.GONE
            // Go to GameOver screen with points
            val intent = Intent(this@StartGame, GameOver::class.java)
            intent.putExtra("points", points)
            startActivity(intent)
            // Finish StartGame Activity
            finish()
        } else {
            // Till there is at least one question left, initialize countDownTimer with null and
            // call startGame()
            countDownTimer = null
            startGame()
        }
    }

    fun answerSelected(view: View) {
        // Change the clicked Button's background color
        view.setBackgroundColor(Color.parseColor("#17243e"))
        // Disable all four Buttons
        btn1!!.isEnabled = false
        btn2!!.isEnabled = false
        btn3!!.isEnabled = false
        btn4!!.isEnabled = false
        // The user has selected an answer, so, cancel the countDownTimer
        countDownTimer!!.cancel()
        // Get clicked button's text for user answer
        val answer = (view as Button).text.toString().trim { it <= ' ' }
        // And, get the correct answer for the current question from logoList using index
        // as position.
        val correctAnswer = logoList[index]
        // Compare answer and correctAnswer, that is, the answer selected by the user
        // and the correct answer for this question.
        if (answer == correctAnswer) {
            // If true, the user has selected the right answer. So, increment points.
            points++
            // Here we are incrementing points by 1 here, but, you can increment by any number
            // you want.
            // Update the TextViews for points and result
            tvPoints!!.text = points.toString() + " / " + logoList.size
            tvResult!!.text = "Correct"
        } else {
            // In else, that is, when the user answer is incorrect, show "Wrong" in tvResult.
            tvResult!!.text = "Wrong"
        }
    }
}