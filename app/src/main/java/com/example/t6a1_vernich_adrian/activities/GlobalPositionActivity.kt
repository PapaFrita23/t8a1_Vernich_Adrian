package com.example.t6a1_vernich_adrian.activities

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.t6a1_vernich_adrian.R
import com.example.t6a1_vernich_adrian.databinding.ActivityGlobalPositionBinding
import com.example.t6a1_vernich_adrian.fragments.AccountsFragment
import com.example.t6a1_vernich_adrian.listener.OnClickListener
import com.example.t6a1_vernich_adrian.pojo.Cliente
import com.example.t6a1_vernich_adrian.pojo.Cuenta

class GlobalPositionActivity : AppCompatActivity(), OnClickListener {
    private lateinit var binding: ActivityGlobalPositionBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGlobalPositionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtén el cliente desde el Intent
        val cliente = intent.getSerializableExtra("Cliente") as Cliente

        if (isTablet()) {
            // Tablet: Mostrar ambos fragments
            loadFragment(AccountsFragment(),cliente)
        }else{
            // Encuentra el fragmento cargado automáticamente
            val fragAccount = supportFragmentManager.findFragmentById(R.id.fragmentGlobal) as? AccountsFragment
            fragAccount?.setCliente(cliente)
        }

        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.globalPosition)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    private fun isTablet(): Boolean {
        return resources.configuration.smallestScreenWidthDp >= 600
    }

    private fun loadFragment(fragment: Fragment, cliente: Cliente){
        val fragAccount = fragment as? AccountsFragment
        fragAccount?.setCliente(cliente)

        val transaction = supportFragmentManager.beginTransaction()

        if (fragAccount != null) {
            transaction.replace(R.id.fragment_global, fragAccount)
        }

        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onClick(cuenta: Cuenta) {
        TODO("Not yet implemented")
    }
}