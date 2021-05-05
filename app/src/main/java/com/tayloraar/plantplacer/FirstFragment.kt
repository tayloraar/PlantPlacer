package com.tayloraar.plantplacer

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private val viewModel: ItemViewModel by activityViewModels()

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var count = 100
//        view.findViewById<TextView>(R.id.scanTextView).text = viewModel.selectedItem.value
        viewModel.selectedItem.observe(viewLifecycleOwner, Observer { data ->
            view.findViewById<TextView>(R.id.scanTextView).text = data.toString()
        })

        view.findViewById<Button>(R.id.button_first).setOnClickListener {
            findNavController().navigate(R.id.action_FirstFragment_to_SecondFragment)
        }

        while (count > 0){
            view.findViewById<TextView>(R.id.scanTextView).text = viewModel.selectedItem.value
            count --
        }

    }


}