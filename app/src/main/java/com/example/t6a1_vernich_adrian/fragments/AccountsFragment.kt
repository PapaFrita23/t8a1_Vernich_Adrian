package com.example.t6a1_vernich_adrian.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.t6a1_vernich_adrian.R
import com.example.t6a1_vernich_adrian.activities.AccountsAdapter
import com.example.t6a1_vernich_adrian.listener.OnClickListener
import com.example.t6a1_vernich_adrian.activities.GlobalPositionDetailsActivity
import com.example.t6a1_vernich_adrian.activities.MainActivity
import com.example.t6a1_vernich_adrian.bd.MiBancoOperacional
import com.example.t6a1_vernich_adrian.databinding.FragmentAccountsBinding
import com.example.t6a1_vernich_adrian.pojo.Cliente
import com.example.t6a1_vernich_adrian.pojo.Cuenta

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_CLIENTE = "Cliente"

/**
 * A simple [Fragment] subclass.
 * Use the [AccountsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class AccountsFragment : Fragment(), OnClickListener {
    // TODO: Rename and change types of parameters
    private var cliente: Cliente? = null
    private lateinit var accountsAdapter: AccountsAdapter
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var itemDecoration: DividerItemDecoration

    private lateinit var binding: FragmentAccountsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAccountsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mbo: MiBancoOperacional? = MiBancoOperacional.getInstance(context)
        val cuentas: ArrayList<Cuenta> = mbo?.getCuentas(cliente) as ArrayList<Cuenta>

        accountsAdapter = AccountsAdapter(cuentas, this)
        linearLayoutManager = LinearLayoutManager(context)
        itemDecoration = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)

        binding.recyclerView.apply {
            layoutManager = linearLayoutManager
            adapter = accountsAdapter
            addItemDecoration(itemDecoration)
        }

    }

    override fun onClick(cuenta: Cuenta){
        if (isTablet()) {
            // Tablet: Mostrar ambos fragments
            loadFragment(AccountsMovementsFragment(),cuenta)
        }else {
            val intent1 = Intent(context, GlobalPositionDetailsActivity::class.java).apply {
                putExtra("cuenta", cuenta)
            }

            startActivity(intent1)
        }
    }

    fun setCliente(cliente: Cliente) {
        this.cliente = cliente
    }

    private fun isTablet(): Boolean {
        return resources.configuration.smallestScreenWidthDp >= 600
    }

    private fun loadFragment(fragment: Fragment, cuenta: Cuenta){
        val fragAccount = fragment as? AccountsMovementsFragment
        fragAccount?.setCuenta(cuenta)

        val transaction=parentFragmentManager.beginTransaction()

        if (fragAccount != null) {
            transaction.replace(R.id.fragment_movimientos, fragAccount)
        }

        transaction.addToBackStack(null)
        transaction.commit()
    }
}