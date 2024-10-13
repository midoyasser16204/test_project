package com.example.myapplication

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.example.myapplication.databinding.FragmentJobSeekerHomeBinding
import com.example.myapplication.databinding.FragmentSelectionBinding
import com.example.myapplication.databinding.FragmentSignInBinding

class Selection : Fragment() {

    private lateinit var binding: FragmentSelectionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSelectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.cardCompany.setOnClickListener {
            findNavController().navigate(R.id.action_selection_to_companyHome)
        }
        binding.cardJobSeeker.setOnClickListener {
            findNavController().navigate(R.id.action_selection_to_jobSeekerHome)
        }
    }
}