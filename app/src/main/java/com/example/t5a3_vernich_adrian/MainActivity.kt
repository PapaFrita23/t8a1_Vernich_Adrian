package com.example.t5a3_vernich_adrian

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.t5a3_vernich_adrian.databinding.ActivityLoginBinding
import com.example.t5a3_vernich_adrian.databinding.ActivityWelcomeBinding
import com.example.t5a3_vernich_adrian.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnEntrar.setOnClickListener {
            val dni = binding.editTextDNI.text.toString()
            val password = binding.editTextPassword.text.toString()

            if (dni.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            } else {
                val intent = Intent(this, LoginActivity::class.java)
                intent.putExtra("DNI", dni)
                startActivity(intent)
            }
        }

        binding.btnSalirApp.setOnClickListener {
            val intent = Intent(this, WelcomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}