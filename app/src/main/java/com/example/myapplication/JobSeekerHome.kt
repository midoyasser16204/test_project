package com.example.myapplication

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.myapplication.databinding.FragmentJobSeekerHomeBinding
import com.google.firebase.firestore.FirebaseFirestore

class JobSeekerHome : Fragment() {

    private lateinit var binding: FragmentJobSeekerHomeBinding
    private lateinit var firestore: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentJobSeekerHomeBinding.inflate(inflater, container, false)

        firestore = FirebaseFirestore.getInstance()

        // Setup button listener to save user data
        binding.save.setOnClickListener {
            val name = binding.Name.text.toString()
            val age = binding.age.text.toString().toIntOrNull() ?: 0 // Ensure age is an Int
            val phone = binding.phone.text.toString()
            val email = binding.Email.text.toString()
            val skill = binding.skill.text.toString()
            val selectedDisability = binding.disabilitySpinner.selectedItem.toString()

            // Create the DisabilityData object
            val disabilityData = DisabilityData(name = name, age = age, phone = phone, email = email, skill = skill, disability = selectedDisability)

            saveDisabilityData(disabilityData)
        }

        // Get the array from resources
        val disabilityArray = resources.getStringArray(R.array.Disability_Array)

        // Create an ArrayAdapter
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, disabilityArray)

        // Specify the layout for the dropdown items
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        // Set the adapter to the Spinner
        binding.disabilitySpinner.adapter = adapter

        return binding.root
    }


    private fun saveDisabilityData(disabilityData: DisabilityData) {
        val docRef = firestore.collection("disabilityData").document()

        docRef.set(disabilityData)
            .addOnSuccessListener {
                Toast.makeText(context, "Data saved successfully", Toast.LENGTH_SHORT).show()
                findNavController().navigateUp()
            }
            .addOnFailureListener { exception ->
                Toast.makeText(context, "Failed to save data", Toast.LENGTH_SHORT).show()
            }
    }
}
