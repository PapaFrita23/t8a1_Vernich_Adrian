package com.example.t6a1_vernich_adrian.activities

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioButton
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.t6a1_vernich_adrian.R
import com.example.t6a1_vernich_adrian.databinding.ActivityTransferBinding

class TransferActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTransferBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTransferBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        //Declaracion de variables
        val spCuentas: Spinner = findViewById(R.id.spnCuenta)
        val spCuentasP: Spinner = findViewById(R.id.spnCuentaP)
        val spMonedas: Spinner = findViewById(R.id.spinner3)

        val btnCuentaP: RadioButton = findViewById(R.id.btnCuentaP)
        val btnCuentaA: RadioButton = findViewById(R.id.btnCuentaA)

        val editTextCuentaDestino: EditText = findViewById(R.id.editTextCuentaDestino)
        val txtDinero: EditText = findViewById(R.id.txtDinero)
        val checkboxJustificante: CheckBox = findViewById(R.id.checkboxJustificante)

        val btnEnviar: Button = findViewById(R.id.btnEnviar)
        val btnCancelar: Button = findViewById(R.id.btnCancelar)

        //Primer spinner de las cuentas
        val cuentas = resources.getStringArray(R.array.accounts)
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, cuentas)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spCuentas.adapter = adapter
        spCuentas.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val opcionSeleccionada = parent?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        //Segundo spinner de las cuentas
        val cuentas2 = resources.getStringArray(R.array.accounts)
        val adapter2 = ArrayAdapter(this, android.R.layout.simple_spinner_item, cuentas2)
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spCuentasP.adapter = adapter2
        spCuentasP.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val opcionSeleccionada2 = parent?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        //Spinner del tipo de monedas
        val monedas = resources.getStringArray(R.array.money)
        val adapter3 = ArrayAdapter(this, android.R.layout.simple_spinner_item, monedas)
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        spMonedas.adapter = adapter3
        spMonedas.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                val opcionSeleccionada3 = parent?.getItemAtPosition(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        //Cambio de visible a no visible dependiendo del tipo de cuenta q elijas
        btnCuentaP.setOnClickListener {
            spCuentasP.visibility = View.VISIBLE
            editTextCuentaDestino.visibility = View.GONE
        }
        btnCuentaA.setOnClickListener {
            spCuentasP.visibility = View.GONE
            editTextCuentaDestino.visibility = View.VISIBLE
        }

        //Acciones del boton enviar
        btnEnviar.setOnClickListener {
            val cuentaOrigen = spCuentas.selectedItem.toString()
            val cuentaDestino = if (btnCuentaP.isChecked) {
                spCuentasP.selectedItem.toString()
            } else {
                editTextCuentaDestino.text.toString()
            }

            val importe = txtDinero.text.toString()
            val divisa = spMonedas.selectedItem.toString()
            val justificante = if (checkboxJustificante.isChecked) "Sí" else "No"

            if (importe.isNotBlank() && cuentaDestino.isNotBlank()) {
                if (checkboxJustificante.isChecked) {
                    val mensaje = "Cuenta Origen: $cuentaOrigen\nCuenta Destino: $cuentaDestino\nImporte: $importe $divisa\nJustificante: $justificante"
                    Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
                } else {
                    Toast.makeText(this, "Transferencia realizada sin justificante", Toast.LENGTH_LONG).show()
                }

                val mensaje = "Cuenta Origen: $cuentaOrigen\nCuenta Destino: $cuentaDestino\nImporte: $importe $divisa\nJustificante: $justificante"
                Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this, "Por favor, complete todos los campos", Toast.LENGTH_LONG).show()
            }
        }

        //Acciones del boton cancelar
        btnCancelar.setOnClickListener {
            spCuentas.setSelection(0)
            btnCuentaP.isChecked = true
            spCuentasP.visibility = View.VISIBLE
            editTextCuentaDestino.visibility = View.GONE
            spCuentasP.setSelection(0)
            editTextCuentaDestino.text.clear()
            txtDinero.text.clear()
            spMonedas.setSelection(0)
            checkboxJustificante.isChecked = false
        }

        binding.btnCancelar.setOnClickListener {
            finish()
        }
    }
}