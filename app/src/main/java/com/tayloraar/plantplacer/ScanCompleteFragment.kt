package com.tayloraar.plantplacer

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class ScanCompleteFragment : Fragment() {
    private val viewModel: ItemViewModel by activityViewModels()
    private lateinit var frameAnimation: AnimationDrawable
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_scan_complete, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
// Load the ImageView that will host the animation and
        // set its background to our AnimationDrawable XML resource.
       var img = view.findViewById<ImageView>(R.id.animationImg2).apply {
           setBackgroundResource(R.drawable.animationloader)
           frameAnimation = background as AnimationDrawable
       }

        loadedScan(view)
        // Start the animation (looped playback by default).
        frameAnimation.start();



        view.findViewById<Button>(R.id.button_second).setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }
    }

fun loadedScan(view: View){
    viewModel.selectedItem.observe(viewLifecycleOwner, Observer { data ->
        view.findViewById<TextView>(R.id.scanResTV).text = data.toString()
    })
    (activity as ScanActivity).pauseScan(view)
}
}