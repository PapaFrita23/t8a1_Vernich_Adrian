package com.example.t6a1_vernich_adrian.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.t6a1_vernich_adrian.databinding.ActivityChancePasswdBinding
import com.example.t6a1_vernich_adrian.pojo.Cliente
import com.example.t6a1_vernich_adrian.bd.MiBancoOperacional

class ChancePasswdActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChancePasswdBinding
    lateinit var editTextPassword: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChancePasswdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCambiar.setOnClickListener {
            val mbo: MiBancoOperacional? = MiBancoOperacional.getInstance(this)
            editTextPassword = binding.editTextPassword.text.toString()

            val cliente = intent.getSerializableExtra("Cliente") as? Cliente
            if (cliente != null) {
                cliente.setClaveSeguridad(editTextPassword)
            }

            val passwd = mbo?.changePassword(cliente)
            if(passwd == -1) {
                Toast.makeText(this, "La constraseña no se ha podido cambiar", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "Contraseña cambiada a : ${editTextPassword}", Toast.LENGTH_SHORT).show()

                finish()
            }
        }

        binding.btnCancelar.setOnClickListener {
            finish()
        }
    }
}