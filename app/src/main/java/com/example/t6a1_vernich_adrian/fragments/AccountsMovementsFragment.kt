package com.example.t6a1_vernich_adrian.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.t6a1_vernich_adrian.R
import com.example.t6a1_vernich_adrian.activities.MovementsAdapter
import com.example.t6a1_vernich_adrian.bd.MiBancoOperacional
import com.example.t6a1_vernich_adrian.databinding.FragmentAccountsBinding
import com.example.t6a1_vernich_adrian.databinding.FragmentAccountsMovementsBinding
import com.example.t6a1_vernich_adrian.pojo.Cliente
import com.example.t6a1_vernich_adrian.pojo.Cuenta
import com.example.t6a1_vernich_adrian.pojo.Movimiento

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_CUENTA = "cuenta"

/**
 * A simple [Fragment] subclass.
 * Use the [AccountsMovementsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AccountsMovementsFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var cuenta: Cuenta? = null
    private lateinit var movementsadapter: MovementsAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var itemDecoration: DividerItemDecoration

    private lateinit var binding: FragmentAccountsMovementsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAccountsMovementsBinding.inflate(inflater, container, false)

        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mbo: MiBancoOperacional? = MiBancoOperacional.getInstance(context)
        val movimientos: ArrayList<Movimiento> = mbo?.getMovimientos(cuenta) as ArrayList<Movimiento>

        movementsadapter = MovementsAdapter(movimientos)
        linearLayoutManager = LinearLayoutManager(context)
        itemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)

        binding.recyclerViewMovimientos.apply {
            layoutManager = linearLayoutManager
            adapter = movementsadapter
            addItemDecoration(itemDecoration)
        }
    }

    fun setCuenta(cuenta: Cuenta) {
        this.cuenta = cuenta
    }
}