package com.example.ilmhonazero

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController

class HomeScreenFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_home_screen, container, false)

        val button = view.findViewById<Button>(R.id.navigate_to_next_screen)

        button.setOnClickListener {
            findNavController().navigate(R.id.action_homeScreenFragment_to_secondFragment)
        }


        return view
    }


}