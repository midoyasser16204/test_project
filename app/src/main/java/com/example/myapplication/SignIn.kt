package com.example.myapplication

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.myapplication.databinding.FragmentSignInBinding
import com.google.firebase.auth.FirebaseAuth

class SignIn : Fragment() {
    private lateinit var navController: NavController
    private lateinit var binding: FragmentSignInBinding
    private lateinit var sharedPreferences: SharedPreferences
    lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Initialize SharedPreferences
        sharedPreferences = requireActivity().getSharedPreferences("app_prefs", Context.MODE_PRIVATE)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Initialize the binding
        binding = FragmentSignInBinding.inflate(inflater, container, false)

        // Set the switch state based on saved language
        binding.languageSwitch.isChecked = sharedPreferences.getString("LANGUAGE", "en") == "ar"

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        auth = FirebaseAuth.getInstance()
        navController = Navigation.findNavController(view)

        binding.btnSignUp.setOnClickListener {

            navController.navigate(R.id.action_signIn_to_signUp)
        }

        binding.SignInButton.setOnClickListener {
            val email = binding.Email.text.toString().trim()
            val password = binding.Password.text.toString().trim()

            if (email.isNotEmpty() && password.isNotEmpty()) {
                signInUser(email, password)
            }
            else {
                Toast.makeText(requireContext(), "Please fill in all fields", Toast.LENGTH_SHORT).show()
            }
        }

        // Set up switch listener
        binding.languageSwitch.setOnCheckedChangeListener { _, isChecked ->
            val newLanguage = if (isChecked) "ar" else "en"
            (activity as? MainActivity)?.applyConfiguration(newLanguage)
            Log.d("LanguageSwitch", "Language changed to: $newLanguage")

        }
    }

    private fun signInUser(email: String, password: String) {
        auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                Toast.makeText(requireContext(), "Sign-In Successful", Toast.LENGTH_SHORT).show()
                navController.navigate(R.id.action_signIn_to_selection)
            } else {
                Toast.makeText(requireContext(), "Sign-In Failed: ${task.exception?.message}", Toast.LENGTH_SHORT).show()
            }
        }
    }



}