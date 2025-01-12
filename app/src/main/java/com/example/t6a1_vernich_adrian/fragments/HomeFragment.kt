package com.example.t6a1_vernich_adrian.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.t6a1_vernich_adrian.R
import com.example.t6a1_vernich_adrian.activities.ChancePasswdActivity
import com.example.t6a1_vernich_adrian.activities.GlobalPositionActivity
import com.example.t6a1_vernich_adrian.activities.MovimientoActivity
import com.example.t6a1_vernich_adrian.activities.TransferActivity
import com.example.t6a1_vernich_adrian.activities.WelcomeActivity
import com.example.t6a1_vernich_adrian.databinding.ActivityLoginBinding
import com.example.t6a1_vernich_adrian.databinding.FragmentHomeBinding
import com.example.t6a1_vernich_adrian.pojo.Cliente

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"
private lateinit var binding: FragmentHomeBinding

/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment(cliente: Cliente) : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var cliente: Cliente? = cliente

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (cliente != null) {
            val nombre = cliente!!.getNombre()
            val apellidos = cliente!!.getApellidos()
            binding.textViewWelcome.text = "Bienvenido/a, $nombre $apellidos"
        } else {
            binding.textViewWelcome.text = "Bienvenido/a"
            Toast.makeText(context, "Error al cargar el cliente logueado", Toast.LENGTH_SHORT).show()
        }

        binding.btnSalir.setOnClickListener {
            startActivity(Intent(context, WelcomeActivity::class.java))
        }
        binding.btnTransferencia.setOnClickListener {
            startActivity(Intent(context, TransferActivity::class.java))
        }
        binding.btnContrasenya.setOnClickListener {
            val intent = Intent(context, ChancePasswdActivity::class.java)
            intent.putExtra("Cliente", cliente)
            startActivity(intent)
        }
        binding.btnPosicionGlobal.setOnClickListener {
            val intent = Intent(context, GlobalPositionActivity::class.java)
            intent.putExtra("Cliente", cliente)
            startActivity(intent)
        }
        binding.btnMovimientos.setOnClickListener {
            val intent = Intent(context, MovimientoActivity::class.java)
            intent.putExtra("Cliente", cliente)
            startActivity(intent)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHomeBinding.inflate(inflater, container, false)

        return binding.root
    }
}