package com.example.t6a1_vernich_adrian.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.t6a1_vernich_adrian.R
import com.example.t6a1_vernich_adrian.databinding.ItemCuentaBinding
import com.example.t6a1_vernich_adrian.listener.onClickListenerCuenta
import com.example.t6a1_vernich_adrian.pojo.Cuenta

class CuentaAdapter(private val listaCuentas: ArrayList<Cuenta>, private val listener: onClickListenerCuenta) : RecyclerView.Adapter<CuentaAdapter.CuentaViewHolder>(){
    private lateinit  var context: Context

    inner class CuentaViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val binding = ItemCuentaBinding.bind(itemView) //Vinculamos la vista a nuestro adapter

        fun setListener(cuenta: Cuenta){
            binding.root.setOnClickListener {
                listener.onItemClick(cuenta)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CuentaViewHolder { //Inflar la vista en el recycler
        context = parent.context
        val view = LayoutInflater.from(context).inflate(R.layout.item_cuenta, parent, false)
        return CuentaViewHolder(view)
    }

    override fun getItemCount(): Int = listaCuentas.size

    override fun onBindViewHolder(holder: CuentaViewHolder, position: Int) {
        val cuenta = listaCuentas[position]

        with(holder){
            setListener(cuenta)

            binding.txtName.text = cuenta.getNumeroCuenta()
            binding.txtMoney.text = cuenta.getSaldoActual().toString()
            if (cuenta.getSaldoActual()!! < 0) {
                binding.txtMoney.setTextColor(context.resources.getColor(R.color.red))
            } else {
                binding.txtMoney.setTextColor(context.resources.getColor(R.color.green))
            }

            Glide.with(context)
                .load(R.drawable.banco)
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .centerCrop()
                .circleCrop()
                .into(binding.imgPhoto)
        }
    }
}
