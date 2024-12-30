package com.example.t6a1_vernich_adrian.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.t6a1_vernich_adrian.R
import com.example.t6a1_vernich_adrian.databinding.ActivityGlobalPositionBinding
import com.example.t6a1_vernich_adrian.databinding.ActivityGlobalPositionDetailsBinding
import com.example.t6a1_vernich_adrian.fragments.AccountsFragment
import com.example.t6a1_vernich_adrian.fragments.AccountsMovementsFragment
import com.example.t6a1_vernich_adrian.pojo.Cliente
import com.example.t6a1_vernich_adrian.pojo.Cuenta

class GlobalPositionDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityGlobalPositionDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGlobalPositionDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtén el cliente desde el Intent
        val cuenta = intent.getSerializableExtra("cuenta") as Cuenta

        // Encuentra el fragmento cargado automáticamente
        val fragAccount = supportFragmentManager.findFragmentById(R.id.fragmentMovements) as? AccountsMovementsFragment
        fragAccount?.setCuenta(cuenta)

        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.globalMovements)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}