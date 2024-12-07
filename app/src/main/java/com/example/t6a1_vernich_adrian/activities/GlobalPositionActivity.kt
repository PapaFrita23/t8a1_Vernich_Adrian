package com.example.t6a1_vernich_adrian.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.t6a1_vernich_adrian.databinding.ActivityGlobalPositionBinding
import com.example.t6a1_vernich_adrian.listener.onClickListenerCuenta
import com.example.t6a1_vernich_adrian.pojo.Cliente
import com.example.t6a1_vernich_adrian.pojo.Cuenta
import com.example.t6a1_vernich_adrian.adapter.CuentaAdapter
import com.example.t6a1_vernich_adrian.bd.MiBancoOperacional

class GlobalPositionActivity : AppCompatActivity(), onClickListenerCuenta {

    private lateinit var binding: ActivityGlobalPositionBinding
    private lateinit var cuentaAdapter: CuentaAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var itemDecoration: DividerItemDecoration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityGlobalPositionBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtener instancia de MiBancoOperacional
        val mbo: MiBancoOperacional? = MiBancoOperacional.getInstance(this)
        // Obtener Cliente desde el Intent
        val cliente = intent.getSerializableExtra("Cliente") as? Cliente
        if (cliente == null) {
            // Maneja el caso donde cliente es nulo, como mostrar un mensaje de error
            Toast.makeText(this, "Cliente no válido", Toast.LENGTH_SHORT).show()
            return
        }
        // Obtener lista de cuentas desde la base de datos, o lista vacía si es nula
        val listaCuentasBD: ArrayList<Cuenta> = mbo?.getCuentas(cliente) as? ArrayList<Cuenta> ?: ArrayList()

        cuentaAdapter = CuentaAdapter(listaCuentasBD, this)
        linearLayoutManager = LinearLayoutManager(this)
        itemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)

        binding.recyclerview.apply {
            layoutManager = linearLayoutManager
            adapter = cuentaAdapter
            addItemDecoration(itemDecoration)
        }
    }

    override fun onItemClick(cuenta: Cuenta) {
        Toast.makeText(this, "Has seleccionado la cuenta ${cuenta.getBanco()}", Toast.LENGTH_SHORT).show()
    }
}
