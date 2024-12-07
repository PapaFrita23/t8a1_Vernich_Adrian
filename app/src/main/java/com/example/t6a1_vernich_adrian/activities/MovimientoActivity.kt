package com.example.t6a1_vernich_adrian.activities

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.t6a1_vernich_adrian.databinding.ActivityMovimientosBinding
import com.example.t6a1_vernich_adrian.listener.onClickListenerMovimiento
import com.example.t6a1_vernich_adrian.pojo.Cliente
import com.example.t6a1_vernich_adrian.pojo.Cuenta
import com.example.t6a1_vernich_adrian.pojo.Movimiento
import com.example.t6a1_vernich_adrian.adapter.MovimientoAdapter
import com.example.t6a1_vernich_adrian.bd.MiBancoOperacional

class MovimientoActivity : AppCompatActivity(), onClickListenerMovimiento {

    // Declaración de propiedades necesarias
    private lateinit var binding: ActivityMovimientosBinding // Binding para acceder a las vistas del layout
    private lateinit var movimientoAdapter: MovimientoAdapter // Adaptador para los movimientos
    private lateinit var linearLayoutManager: LinearLayoutManager // LayoutManager para el RecyclerView
    private lateinit var itemDecoration: DividerItemDecoration // Decoración para separar los ítems del RecyclerView
    private var cuentasArray: ArrayList<Cuenta> = ArrayList() // Lista de cuentas del cliente

    // Método que se ejecuta cuando se crea la actividad
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovimientosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configuración del RecyclerView
        linearLayoutManager = LinearLayoutManager(this)
        itemDecoration = DividerItemDecoration(this, DividerItemDecoration.VERTICAL)

        binding.rvMovimientos.apply {
            layoutManager = linearLayoutManager
            addItemDecoration(itemDecoration)
        }

        // Obtener instancia de la base de datos y el cliente
        val mbo: MiBancoOperacional? = MiBancoOperacional.getInstance(this)
        val cliente = intent.getSerializableExtra("Cliente") as? Cliente
        if (cliente == null) { // Validar si el cliente es nulo
            Toast.makeText(this, "Cliente no válido", Toast.LENGTH_SHORT).show()
            finish()
            return
        }

        // Cargar cuentas del cliente desde la base de datos
        cuentasArray = mbo?.getCuentas(cliente) as? ArrayList<Cuenta> ?: ArrayList()
        if (cuentasArray.isEmpty()) {
            Toast.makeText(this, "El cliente no tiene cuentas", Toast.LENGTH_SHORT).show()
            return
        }

        // Configurar el Spinner para mostrar las cuentas del cliente
        configurarSpinnerCuentas()

        // Mostrar los movimientos de la primera cuenta por defecto
        actualizarMovimientos(cuentasArray[0])
    }

    // Método para configurar el Spinner con las cuentas del cliente
    private fun configurarSpinnerCuentas() {
        // Obtener los nombres de las cuentas
        val nombresCuentas = cuentasArray.map { it.getNumeroCuenta() }
        // Crear un adaptador para el Spinner con los nombres de las cuentas
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, nombresCuentas)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item) // Vista desplegable del Spinner

        // Asignar el adaptador al Spinner
        binding.spnCuentas.adapter = adapter
        // Configurar un listener para manejar selecciones en el Spinner
        binding.spnCuentas.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                // Cuando se selecciona una cuenta, obtenerla y actualizar los movimientos
                val cuentaSeleccionada = cuentasArray[position]
                actualizarMovimientos(cuentaSeleccionada)
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }
    }

    // Método para actualizar los movimientos del RecyclerView según la cuenta seleccionada
    private fun actualizarMovimientos(cuenta: Cuenta) {
        // Obtener movimientos de la cuenta desde la base de datos
        val mbo: MiBancoOperacional? = MiBancoOperacional.getInstance(this) // Instancia de la base de datos
        val movimientos = mbo?.getMovimientos(cuenta) as? ArrayList<Movimiento> ?: ArrayList() // Movimientos obtenidos

        // Crear un nuevo adaptador para los movimientos
        movimientoAdapter = MovimientoAdapter(movimientos, this)
        // Asignar el adaptador al RecyclerView
        binding.rvMovimientos.adapter = movimientoAdapter

        if (movimientos.isEmpty()) {
            Toast.makeText(this, "No hay movimientos para la cuenta seleccionada", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onItemClick(movimiento: Movimiento) {
        Toast.makeText(this, "Movimiento seleccionado: ${movimiento.getDescripcion()}", Toast.LENGTH_SHORT).show()
    }
}
