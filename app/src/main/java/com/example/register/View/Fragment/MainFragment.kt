package com.example.register.View.Fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.register.R
import com.example.register.ViewModel.LoginViewModel
import com.example.register.local.model.User
import kotlinx.android.synthetic.main.fragment_main.*


class MainFragment : Fragment(), View.OnClickListener {

    lateinit var navControler: NavController

    lateinit var loginViewModel: LoginViewModel


    lateinit var strUsername: String
    lateinit var strPassword: String


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)


    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navControler = Navigation.findNavController(view)
        attachObserve()
        btnRegister.setOnClickListener(this)
        btnLogin.setOnClickListener(this)


    }


    private fun attachObserve() {

        loginViewModel.detailLogin.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer { loginSucces(it) })
        loginViewModel.isError.observe(
            viewLifecycleOwner,
            androidx.lifecycle.Observer { loginError(it) })

    }

    private fun loginError(it: Throwable) {
        Toast.makeText(
            context,
            "  username belum ada coba register terlebih dahulu",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun loginSucces(it: User) {



        navControler.navigate(
            R.id.action_mainFragment_to_homeActivity3

        )
        activity?.finish()


    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnRegister ->
                navControler.navigate(R.id.action_mainFragment_to_register1Fragment)

            R.id.btnLogin -> {
                strUsername = tvid.text.toString().trim()
                strPassword = tvPassword.text.toString().trim()
                loginViewModel.getLogin(strUsername, strPassword)
//                if (strUsername.isEmpty()) {
//
//                    tvid.error = " masukkan username"
//                } else if (strPassword.isEmpty()) {
//                    tvPassword.error = "masukkan password"
//                } else {
//
//
//
//                }
//


            }
        }
    }



}


