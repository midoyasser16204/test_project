package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.myapplication.databinding.FragmentDetailBinding
import com.google.firebase.firestore.FirebaseFirestore

class Detail : Fragment() {

    private lateinit var binding: FragmentDetailBinding
    private lateinit var firestore: FirebaseFirestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        firestore = FirebaseFirestore.getInstance()

        // Get the user ID passed from the previous fragment
        val userId = arguments?.getString("userId")

        if (userId != null) {
            fetchDisabilityData(userId)
        } else {
            Toast.makeText(context, "No user ID found", Toast.LENGTH_SHORT).show()
        }

        return binding.root
    }

    private fun fetchDisabilityData(userId: String) {
        firestore.collection("disabilityData").document(userId).get().addOnSuccessListener { documentSnapshot ->
            if (documentSnapshot.exists()) {
                val disabilityData = documentSnapshot.toObject(DisabilityData::class.java)
                // Display data in the UI
                binding.Name.text = disabilityData?.name
                binding.age.text = disabilityData?.age.toString()
                binding.phone.text = disabilityData?.phone
                binding.Email.text = disabilityData?.email
                binding.skill.text = disabilityData?.skill
                binding.disability.text = disabilityData?.disability
            }
            else {
                Toast.makeText(context, "No data found", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener {
            Toast.makeText(context, "Failed to fetch data", Toast.LENGTH_SHORT).show()
        }
    }
}
