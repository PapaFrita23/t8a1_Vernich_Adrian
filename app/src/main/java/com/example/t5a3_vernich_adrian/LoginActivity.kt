package com.example.t5a3_vernich_adrian

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.t5a3_vernich_adrian.databinding.ActivityWelcomeBinding
import com.example.t5a3_vernich_adrian.databinding.ActivityMainBinding
import com.example.t5a3_vernich_adrian.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val dni = intent.getStringExtra("DNI")
        binding.textViewWelcome.text = "Bienvenido/a, $dni"

        binding.btnSalir.setOnClickListener {
            finish()
        }

        binding.btnTransferencia.setOnClickListener {
            val intent = Intent(this, TransferActivity::class.java)
            startActivity(intent)
        }
    }
}