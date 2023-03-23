package com.example.ilmhonazero

import android.os.Bundle
import android.os.CountDownTimer
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val TIMER_DURATION = 10000L //timer duration
        var timerIsStarted = false //timer state
        var clicks = 0 //score clicks
        var highScore = 0 //highest score

        //UI elements
        val scoreButton = findViewById<TextView>(R.id.score_button)
        val timerTextView = findViewById<TextView>(R.id.timer_tv)
        val highScoreTextView = findViewById<TextView>(R.id.highscore_tv)

        timerTextView.text = getString(R.string.timer_string, 0)
        highScoreTextView.text = getString(R.string.highscore_string, 0)

        val timer = object : CountDownTimer(TIMER_DURATION, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                timerTextView.text = getString(R.string.timer_string, millisUntilFinished / 1000L)
            }

            override fun onFinish() {
                //set highscore
                if (clicks > highScore) {
                    highScore = clicks
                    highScoreTextView.text = getString(R.string.highscore_string, highScore)
                }
                //reset score
                timerIsStarted = false
                clicks = 0
                scoreButton.text = clicks.toString()
            }
        }

        scoreButton.setOnClickListener {
            if (!timerIsStarted) {
                timer.start()
                timerIsStarted = true
            } else {
                clicks++
                scoreButton.text = clicks.toString()
            }
        }

    }
}