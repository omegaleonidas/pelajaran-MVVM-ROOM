package com.example.register.View.Fragment

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.register.R
import com.example.register.ViewModel.LoginViewModel
import com.example.register.local.model.User
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        attachObserve()

        navController = Navigation.findNavController(view)

        btnNext.setOnClickListener(this)
        tvStep1.setOnClickListener(this)
    }


    private fun validate(): Boolean {
        val Email = tvEmailRegister.text.toString()
        if (Patterns.EMAIL_ADDRESS.matcher(Email).matches()) {

            return true
        } else {
            tvEmailRegister.error = "Email tidak valid"
            return false
        }
    }


    private fun attachObserve() {
        loginViewModel.detailLCekLogin.observe(viewLifecycleOwner, Observer { cekSucces(it) })
        loginViewModel.isError.observe(viewLifecycleOwner, Observer { cekError(it) })

    }

    private fun cekError(it :Throwable) {

        Log.d("TAG", "validasi username $")
        if (tvIdRegister.text.toString().isEmpty()) {
            tvIdRegister.error = " id harus di isi"
        } else if (tvEmailRegister.text.toString().isEmpty()) {
            tvEmailRegister.error = "email harus di isi"


        } else if (!validate()) {


        } else {
            val bundle = bundleOf(
                "name" to tvIdRegister.text.toString(),
                "email" to tvEmailRegister.text.toString()

            )


            navController.navigate(R.id.register2Fragment, bundle)


        }
    }

    private fun cekSucces(it: User) {


        Toast.makeText(
            context,
            "username dan email ada yang sama coba lagi ",
            Toast.LENGTH_SHORT
        ).show()
    }


    override fun onClick(v: View?) {


        when (v!!.id) {


            R.id.btnNext -> {

                strusernname = tvIdRegister.text.toString().trim()
                stremail = tvEmailRegister.text.toString().trim()

                loginViewModel.getCekRegister(strusernname, stremail)


            }
            R.id.tvStep1 -> activity?.onBackPressed()
        }
    }
}







