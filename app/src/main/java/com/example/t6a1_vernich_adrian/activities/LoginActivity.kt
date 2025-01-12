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
import androidx.fragment.app.Fragment
import com.example.t6a1_vernich_adrian.R
import com.example.t6a1_vernich_adrian.databinding.ActivityLoginBinding
import com.example.t6a1_vernich_adrian.fragments.AccountsFragment
import com.example.t6a1_vernich_adrian.fragments.AccountsMovementsFragment
import com.example.t6a1_vernich_adrian.fragments.HomeFragment
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

        drawerLayout = findViewById(R.id.main)
        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        val toggle = ActionBarDrawerToggle(
            this, drawerLayout, toolbar, R.string.open_nav, R.string.close_nav
        )
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()

        if (savedInstanceState == null) {
            cliente?.let { HomeFragment(it) }?.let {
                supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, it).commit()
            }
            navigationView.setCheckedItem(R.id.nav_home)

        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_home -> {
                startActivity(Intent(this, MainActivity::class.java))
            }
            R.id.nav_posGlobal -> {
                loadFragment(AccountsFragment(), cliente!!)
            }
            R.id.nav_movimientos -> {
                val intent = Intent(this, MovimientoActivity::class.java)
                intent.putExtra("Cliente", cliente)
                startActivity(intent)
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

    private fun loadFragment(fragment: Fragment, cliente: Cliente) {
        val fragmentAccount = fragment as? AccountsFragment
        fragmentAccount?.setCliente(cliente)

        val transaction = supportFragmentManager.beginTransaction()
        if (fragmentAccount != null) {
            transaction.replace(R.id.fragment_container, fragmentAccount)
        }
        transaction.addToBackStack(null)
        transaction.commit()
    }

    override fun onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }
}
