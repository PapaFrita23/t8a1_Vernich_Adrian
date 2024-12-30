package com.example.t6a1_vernich_adrian.activities

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.t6a1_vernich_adrian.activities.MovimientoActivity
import com.example.t6a1_vernich_adrian.R
import com.example.t6a1_vernich_adrian.databinding.ItemMovimientosBinding
import com.example.t6a1_vernich_adrian.pojo.Movimiento

class MovAdapter(private val movimientos: ArrayList<Movimiento>?, private val listener: MovimientoActivity) : RecyclerView.Adapter<MovAdapter.ViewHolder>() {
    private lateinit var context: Context

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemMovimientosBinding.bind(view)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context

        val view = LayoutInflater.from(context).inflate(R.layout.item_movimientos, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val movi1 = movimientos?.get(position)

        with(holder) {
            if (movi1 != null) {
                binding.txtNameM.text=movi1.getDescripcion()+" "+movi1.getFechaOperacion()+" "+movi1.getImporte()
            }
        }
    }

    override fun getItemCount(): Int {
        return movimientos?.size ?: 0
    }
}