package com.example.t6a1_vernich_adrian.activities

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.t6a1_vernich_adrian.R
import com.example.t6a1_vernich_adrian.databinding.ItemCuentaBinding
import com.example.t6a1_vernich_adrian.fragments.AccountsFragment
import com.example.t6a1_vernich_adrian.pojo.Cuenta
import com.example.t6a1_vernich_adrian.pojo.Movimiento

class MovementsAdapter(private val movimientos: ArrayList<Movimiento>?) : RecyclerView.Adapter<MovementsAdapter.ViewHolder>() {
    private lateinit var context: Context

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemCuentaBinding.bind(view)
        fun setListener(cuenta: Cuenta) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context

        val view = LayoutInflater.from(context).inflate(R.layout.item_cuenta, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movimientos?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val moviment = movimientos?.get(position)

        with(holder) {
            if (moviment != null) {
                binding.txtName.text = moviment.getDescripcion()+" "+moviment.getFechaOperacion()+" "+moviment.getImporte()
            }
        }
    }
}
