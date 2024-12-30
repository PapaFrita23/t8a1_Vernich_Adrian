package com.example.t6a1_vernich_adrian.activities

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.t6a1_vernich_adrian.R
import com.example.t6a1_vernich_adrian.activities.GlobalPositionActivity
import com.example.t6a1_vernich_adrian.databinding.ItemCuentaBinding
import com.example.t6a1_vernich_adrian.fragments.AccountsFragment
import com.example.t6a1_vernich_adrian.pojo.Cuenta

class AccountsAdapter(private val cuentas: ArrayList<Cuenta>?, private val listener: AccountsFragment) : RecyclerView.Adapter<AccountsAdapter.ViewHolder>() {
    private lateinit var context: Context

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding = ItemCuentaBinding.bind(view)

        fun setListener(cuenta: Cuenta) {
            binding.root.setOnClickListener {
                listener.onClick(cuenta)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        context = parent.context

        val view = LayoutInflater.from(context).inflate(R.layout.item_cuenta, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return cuentas?.size ?: 0
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cuenta1 = cuentas?.get(position)

        with(holder) {
            if (cuenta1 != null) {
                setListener(cuenta1)
            }

            binding.txtName.text = cuenta1?.getNumeroCuenta()
        }
    }
}
