package com.example.ilmhonazero

import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.view.animation.Animation
import android.view.animation.Animation.AnimationListener
import android.view.animation.Animation.INFINITE
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val enlargeAnimation = AnimationUtils.loadAnimation(this, R.anim.enlarge)
        val jumpAnimationUp = AnimationUtils.loadAnimation(this, R.anim.jump_animation_up)
        val jumpAnimationDown = AnimationUtils.loadAnimation(this, R.anim.jump_animation_down)
        val jumpAnimationUp1 = AnimationUtils.loadAnimation(this, R.anim.jump_animation_up)
        val jumpAnimationDown1 = AnimationUtils.loadAnimation(this, R.anim.jump_animation_down)
        val jumpAnimationUp2 = AnimationUtils.loadAnimation(this, R.anim.jump_animation_up)
        val jumpAnimationDown2 = AnimationUtils.loadAnimation(this, R.anim.jump_animation_down)

        //UI elements
        val scoreButton = findViewById<TextView>(R.id.score_button)
        val timerTextView = findViewById<TextView>(R.id.timer_tv)
        val highScoreTextView = findViewById<TextView>(R.id.highscore_tv)

        val dot1 = findViewById<TextView>(R.id.textView)
        val dot2 = findViewById<TextView>(R.id.textView2)
        val dot3 = findViewById<TextView>(R.id.textView3)

        dot1.startJumpAnimation(jumpAnimationUp, jumpAnimationDown)
        GlobalScope.launch {
            delay(200)
            dot2.startJumpAnimation(jumpAnimationUp1, jumpAnimationDown1)
            delay(200)
            dot3.startJumpAnimation(jumpAnimationUp2, jumpAnimationDown2)
        }

        val TIMER_DURATION = 10000L //timer duration
        var timerIsStarted = false //timer state
        var clicks = 0 //score clicks
        var highScore = 0 //highest score

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
            it.startJumpAnimation(jumpAnimationUp, jumpAnimationDown)
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

fun View.startJumpAnimation(animation1: Animation, animation2: Animation) {
    this.startAnimation(animation1)
    animation1.setAnimationListener(object : AnimationListener {
        override fun onAnimationStart(animation: Animation?) {

        }

        override fun onAnimationEnd(animation: Animation?) {
            this@startJumpAnimation.startAnimation(animation2)
        }

        override fun onAnimationRepeat(animation: Animation?) {
        }
    })
    animation2.setAnimationListener(object : AnimationListener {
        override fun onAnimationStart(animation: Animation?) {

        }

        override fun onAnimationEnd(animation: Animation?) {
            this@startJumpAnimation.startAnimation(animation1)
        }

        override fun onAnimationRepeat(animation: Animation?) {
        }
    })
}