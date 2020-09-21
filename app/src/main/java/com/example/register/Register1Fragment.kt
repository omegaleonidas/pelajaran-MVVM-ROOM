package com.example.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.register.ViewModel.JadwalViewModel
import com.example.register.ViewModel.LoginViewModel
import kotlinx.android.synthetic.main.dialog_from_jadwal.view.*
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_register1.*

class Register1Fragment : Fragment(), View.OnClickListener {

    lateinit var navController: NavController
    lateinit var strusernname: String
    lateinit var stremail: String
    lateinit var loginViewModel: LoginViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register1, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)

        btnNext.setOnClickListener(this)
        tvStep1.setOnClickListener(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.btnNext -> {

                if (tvIdRegister.text.toString().isEmpty()) {
                    tvIdRegister.error = " id harus di isi"
                } else if (tvEmailRegister.text.toString().isEmpty()) {
                    tvEmailRegister.error = "email harus di isi"

                } else {
                    val bundle = bundleOf(
                        "name" to tvIdRegister.text.toString(),
                        "email" to tvEmailRegister.text.toString()

                    )


                    navController!!.navigate(R.id.register2Fragment, bundle)



                }


            }
            R.id.tvStep1 -> navController!!.navigate(R.id.mainFragment)
        }
    }

}