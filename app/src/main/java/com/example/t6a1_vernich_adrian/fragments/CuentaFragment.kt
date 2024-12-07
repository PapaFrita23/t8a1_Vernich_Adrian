package com.example.t6a1_vernich_adrian.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.t6a1_vernich_adrian.R
import com.example.t6a1_vernich_adrian.databinding.FragmentCuentaBinding
import com.example.t6a1_vernich_adrian.listener.onClickListenerCuenta
import com.example.t6a1_vernich_adrian.pojo.Cliente

/**
 * A simple [Fragment] subclass.
 * Use the [CuentaFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
private const val ARG_CLIENTE = "cliente"

class CuentaFragment : Fragment() {


    // TODO: Rename and change types of parameters
    private lateinit var binding: FragmentCuentaBinding

    private lateinit var cliente: Cliente
    private lateinit var listener: onClickListenerCuenta

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            cliente = it.getSerializable(ARG_CLIENTE) as Cliente
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cuenta, container, false)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment CuentaFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(c: Cliente) =
            CuentaFragment().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_CLIENTE, c)
                }
            }
    }
}