package com.tayloraar.plantplacer

import android.content.Intent
import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import androidx.navigation.fragment.findNavController

class ScanSplash : Fragment() {
    private lateinit var frameAnimation: AnimationDrawable

    private val SPLASH_TIME_OUT:Long = 5000 // 1 sec

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
             return inflater.inflate(R.layout.fragment_scan_splash, container, false)

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var img = view.findViewById<ImageView>(R.id.animationImg2).apply {
            setBackgroundResource(R.drawable.animationloader)
            frameAnimation = background as AnimationDrawable
        }

        frameAnimation.start();

        Handler().postDelayed({
              findNavController().navigate(R.id.action_scanSplash_to_SecondFragment)
        }, SPLASH_TIME_OUT)
    }
}