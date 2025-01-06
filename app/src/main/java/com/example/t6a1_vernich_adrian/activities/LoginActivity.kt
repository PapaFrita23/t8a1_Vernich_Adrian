package com.example.t6a1_vernich_adrian.activities

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.example.t6a1_vernich_adrian.R
import com.example.t6a1_vernich_adrian.databinding.ActivityLoginBinding
import com.example.t6a1_vernich_adrian.fragments.AccountsFragment
import com.example.t6a1_vernich_adrian.fragments.AccountsMovementsFragment
import com.example.t6a1_vernich_adrian.pojo.Cliente
import com.google.android.material.navigation.NavigationView

class LoginActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var drawerLayout: DrawerLayout
    private var cliente: Cliente? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cliente = intent.getSerializableExtra("Cliente") as? Cliente

        if (cliente != null) {
            val nombre = cliente!!.getNombre()
            val apellidos = cliente!!.getApellidos()
            binding.textViewWelcome.text = "Bienvenido/a, $nombre $apellidos"
        } else {
            binding.textViewWelcome.text = "Bienvenido/a"
            Toast.makeText(this, "Error al cargar el cliente logueado", Toast.LENGTH_SHORT).show()
        }

        binding.btnSalir.setOnClickListener { finish() }
        binding.btnTransferencia.setOnClickListener {
            startActivity(Intent(this, TransferActivity::class.java))
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

        drawerLayout = findViewById(R.id.drawer_layout)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> {
                finish()
            }
            R.id.nav_posGlobal -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, AccountsFragment())
                    .commit()
            }
            R.id.nav_movimientos -> {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, AccountsMovementsFragment())
                    .commit()
            }
            R.id.nav_transferencias -> {
                val intent = Intent(this, TransferActivity::class.java)
                startActivity(intent)
            }
            R.id.nav_cambiarPass -> {
                val intent = Intent(this, ChancePasswdActivity::class.java)
                intent.putExtra("Cliente", cliente)
                startActivity(intent)
            }
            R.id.nav_logout -> finish()
            else -> Toast.makeText(this, "Opción no implementada", Toast.LENGTH_SHORT).show()
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}
