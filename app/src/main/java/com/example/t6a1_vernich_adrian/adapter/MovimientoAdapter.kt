package com.example.t6a1_vernich_adrian.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.t6a1_vernich_adrian.R
import com.example.t6a1_vernich_adrian.databinding.ItemMovimientosBinding
import com.example.t6a1_vernich_adrian.listener.onClickListenerMovimiento
import com.example.t6a1_vernich_adrian.pojo.Movimiento

class MovimientoAdapter(private val listaMovimientos: ArrayList<Movimiento>, private val listener: onClickListenerMovimiento) : RecyclerView.Adapter<MovimientoAdapter.MovimientoViewHolder>() {
    private lateinit  var context: Context

    inner class MovimientoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemMovimientosBinding.bind(itemView) //Vinculamos la vista a nuestro adapter

        fun setListener(movimiento: Movimiento){
            binding.root.setOnClickListener {
                listener.onItemClick(movimiento)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovimientoViewHolder {
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_movimientos, parent, false)
        return MovimientoViewHolder(view)
    }

    override fun getItemCount(): Int = listaMovimientos.size

    override fun onBindViewHolder(holder: MovimientoViewHolder, position: Int) {
        val movimiento = listaMovimientos[position]

        with(holder){
            setListener(movimiento)

            binding.txtNameM.text= movimiento.getDescripcion()
            binding.txtDatosM.text = movimiento.getFechaOperacion().toString() + "Importe: " + movimiento.getImporte()
            Glide.with(context)
                .load(R.drawable.cerdo)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .circleCrop()
                .into(binding.imgPhoto)
        }
    }
}