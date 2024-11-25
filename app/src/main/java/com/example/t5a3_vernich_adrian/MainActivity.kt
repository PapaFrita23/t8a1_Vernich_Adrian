package com.example.t5a3_vernich_adrian

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.t5a3_vernich_adrian.bd.MiBancoOperacional
import com.example.t5a3_vernich_adrian.databinding.ActivityLoginBinding
import com.example.t5a3_vernich_adrian.databinding.ActivityWelcomeBinding
import com.example.t5a3_vernich_adrian.databinding.ActivityMainBinding
import com.example.t5a3_vernich_adrian.pojo.Cliente

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnEntrar.setOnClickListener {
            val mbo: MiBancoOperacional? = MiBancoOperacional.getInstance(this)

            val dni = binding.editTextDNI.text.toString()
            val password = binding.editTextPassword.text.toString()

            var Cliente = Cliente()
            Cliente.setNif(dni)
            Cliente.setClaveSeguridad(password)

            val ClienteLogueado = mbo?.login(Cliente) ?: -1
            Log.i("dni",dni)
            if (dni.isEmpty() || password.isEmpty()) {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
            } else if (ClienteLogueado != -1) {
                val intent = Intent(this, LoginActivity::class.java)
                intent.putExtra("Cliente", ClienteLogueado)
                startActivity(intent)
            } else {
                Toast.makeText(this, "Este usuario no esta dentro de la base de datos", Toast.LENGTH_SHORT).show()
            }
        }

        binding.btnSalirApp.setOnClickListener {
            val intent = Intent(this, WelcomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }
}