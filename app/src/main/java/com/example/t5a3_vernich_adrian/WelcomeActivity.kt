package com.example.t5a3_vernich_adrian

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.t5a3_vernich_adrian.databinding.ActivityMainBinding
import com.example.t5a3_vernich_adrian.databinding.ActivityLoginBinding
import com.example.t5a3_vernich_adrian.databinding.ActivityWelcomeBinding

class WelcomeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWelcomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWelcomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnBienvenida.setOnClickListener() {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
        }

        binding.btnSalidaApp.setOnClickListener() {
            finish()
        }
    }
}