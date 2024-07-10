package com.example.bemyguess

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.lang.Exception
import kotlin.random.Random

var answer = 0
var isGameOver = false
var noOfAttempts = 0

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        startOver()
    }

    fun generateAnswer() {
        answer = Random.nextInt(1, 25)
    }

    fun startOver() {
        isGameOver = false
        generateAnswer()
        noOfAttempts = 0

        val answerTextView = findViewById<TextView>(R.id.answer)
        answerTextView.text = "??"

        val submitButton = findViewById<Button>(R.id.submit_btn)
        submitButton.isEnabled = true

        val textView = findViewById<TextView>(R.id.textView)
        textView.text = "Guess 1 to 25"

        val editTextGuess = findViewById<EditText>(R.id.editTextNumber)
        editTextGuess.text.clear()
    }

    fun btnStartOverTapped(view: View) {
        startOver()
    }

    fun btnSubmitTapped(view: View) {

        val guess = getUserGuess() ?: -999

        val textView = findViewById<TextView>(R.id.textView)
        if (guess !in 1..25) {
            textView.text = "Guess must be 1 to 25"
            return
        }

        var message = ""
        noOfAttempts++

        if (guess == answer) {
            message = "Correct! Guess(es): $noOfAttempts"
            isGameOver = true

            val answerTextView = findViewById<TextView>(R.id.answer)
            answerTextView.text = answer.toString()

            val submitButton = findViewById<Button>(R.id.submit_btn)
            submitButton.isEnabled = false

        }
        else {
            message = if (guess < answer) "Too low!" else "Too high!"
        }

        textView.text = message
    }

    fun getUserGuess(): Int? {
        val editTextGuess = findViewById<EditText>(R.id.editTextNumber)
        val userGuess = editTextGuess.text.toString()

        var guessAsInt = 0

        try {
            guessAsInt = userGuess.toInt()
        } catch (e: Exception) {
            return null
        }

        return guessAsInt
    }

}