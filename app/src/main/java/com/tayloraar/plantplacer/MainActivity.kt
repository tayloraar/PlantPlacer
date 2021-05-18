package com.tayloraar.plantplacer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatDelegate

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        findViewById<Button>(R.id.scanBtn2).setOnClickListener {
            startActivity(Intent(this, ScanActivity::class.java))
        }
        findViewById<Button>(R.id.manInpBtn).setOnClickListener {
            startActivity(Intent(this, SelfInput::class.java))
        }
    }
    fun onScanClick(view: View){
    startActivity(Intent(this, ScanActivity::class.java))
    }
}