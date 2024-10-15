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
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initialize SharedPreferences in onCreate, before it is used
        sharedPreferences = requireActivity().getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentSelectionBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Save role as "company" when company is selected
        binding.cardCompany.setOnClickListener {
            sharedPreferences.edit().putString("USER_ROLE", "company").apply()
            findNavController().navigate(R.id.action_selection_to_signIn)
        }

        // Save role as "job_seeker" when job seeker is selected
        binding.cardJobSeeker.setOnClickListener {
            sharedPreferences.edit().putString("USER_ROLE", "job_seeker").apply()
            findNavController().navigate(R.id.action_selection_to_signIn)
        }
    }
}
