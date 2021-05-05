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

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER



class ScanSplash : Fragment() {
    private lateinit var frameAnimation: AnimationDrawable
    // This is the loading time of the splash screen
    private val SPLASH_TIME_OUT:Long = 5000 // 1 sec

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_scan_splash, container, false)
        // set its background to our AnimationDrawable XML resource.


    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
// Load the ImageView that will host the animation and
        // set its background to our AnimationDrawable XML resource.
        var img = view.findViewById<ImageView>(R.id.animationImg2).apply {
            setBackgroundResource(R.drawable.animationloader)
            frameAnimation = background as AnimationDrawable
        }



        // Start the animation (looped playback by default).
        frameAnimation.start();


        Handler().postDelayed({
            // This method will be executed once the timer is over
            // Start your app main activity

            findNavController().navigate(R.id.action_scanSplash_to_SecondFragment)

            // close this activity

        }, SPLASH_TIME_OUT)



    }

}