package com.example.t6a1_vernich_adrian.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.t6a1_vernich_adrian.databinding.ActivityLoginBinding
import com.example.t6a1_vernich_adrian.pojo.Cliente

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val cliente = intent.getSerializableExtra("Cliente") as? Cliente
        if (cliente != null) {
            val nombre = cliente.getNombre()
            val apellidos = cliente.getApellidos()

            binding.textViewWelcome.text = "Bienvenido/a, $nombre $apellidos"
        } else {
            binding.textViewWelcome.text = "Bienvenido/a"
            // Manejar error si no hay cliente válido
            Toast.makeText(this, "Error al cargar el cliente logueado", Toast.LENGTH_SHORT).show()
        }


        binding.btnSalir.setOnClickListener {
            finish()
        }

        binding.btnTransferencia.setOnClickListener {
            val intent = Intent(this, TransferActivity::class.java)
            startActivity(intent)
        }

        binding.btnContrasenya.setOnClickListener {
            val intent = Intent(this, ChancePasswdActivity::class.java)
            intent.putExtra("Cliente", cliente)

            startActivity(intent)
        }

        binding.btnPosicionGlobal.setOnClickListener {
            val intent = Intent(this, GlobalPositionActivity::class.java)
            intent.putExtra("Cliente", cliente)
            startActivity(intent)
        }

        binding.btnMovimientos.setOnClickListener {
            val intent = Intent(this, MovimientoActivity::class.java)
            intent.putExtra("Cliente", cliente)
            startActivity(intent)
        }
    }
}