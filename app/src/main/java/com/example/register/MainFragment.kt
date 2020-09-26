package com.example.register

import android.content.Context
import android.content.Intent
import android.nfc.Tag
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.SurfaceControl
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.register.Home.HomeActivity
import com.example.register.ViewModel.LoginViewModel
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.fragment_register1.*
import kotlin.math.log


class MainFragment : Fragment(), View.OnClickListener {

    lateinit var navControler: NavController

    lateinit var loginViewModel: LoginViewModel


    lateinit var strUsername: String
    lateinit var strPassword: String

    companion object {

        const val LOGIN_SESSION = "login_session"
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_main, container, false)


    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navControler = Navigation.findNavController(view)
        btnRegister.setOnClickListener(this)
        btnLogin.setOnClickListener(this)
        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)


    }


    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnRegister ->
                navControler!!.navigate(R.id.action_mainFragment_to_register1Fragment)

            R.id.btnLogin -> {
                strUsername = tvid.text.toString().trim()
                strPassword = tvPassword.text.toString().trim()

                if (strUsername.isEmpty()) {

                    tvid.error = " masukkan username"
                } else if (strPassword.isEmpty()) {
                    tvPassword.error = "masukkan password"
                } else {
                  loginViewModel.getLoginDetails(requireContext(),strUsername,strPassword)!!
                        .observe(viewLifecycleOwner, Observer {
                            Log.d("TAG","datamasuk $it")
                            if (it == null) {
                                Toast.makeText(
                                    context,
                                    "data Tidak ada mohon untuk register ",
                                    Toast.LENGTH_SHORT
                                ).show()

                            } else {
                                val intent = Intent(context, HomeActivity::class.java)
                                intent.putExtra(LOGIN_SESSION, strUsername)
                                startActivity(intent)

                                val bundle = bundleOf(
                                    "name" to tvid.text.toString().trim()
                                )
                                navControler!!.navigate(
                                    R.id.action_mainFragment_to_berandaFragment2,
                                    bundle
                                )


                            }
                        })

                }
//


            }
        }
    }


}


