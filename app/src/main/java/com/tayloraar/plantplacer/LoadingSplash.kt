package com.tayloraar.plantplacer

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.ImageView

class LoadingSplash : AppCompatActivity() {
    private lateinit var frameAnimation: AnimationDrawable
    // This is the loading time of the splash screen
    private val SPLASH_TIME_OUT:Long = 5000 // 1 sec
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_loading_splash)

        var img = findViewById<ImageView>(R.id.animationImg2).apply {
            setBackgroundResource(R.drawable.animationloader)
            frameAnimation = background as AnimationDrawable
        }

        frameAnimation.start();

        Handler().postDelayed({

            startActivity(Intent(this,MainActivity::class.java))

            finish()
        }, SPLASH_TIME_OUT)

    }
}