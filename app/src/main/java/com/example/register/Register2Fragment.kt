package com.example.register

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.example.register.ViewModel.LoginViewModel
import kotlinx.android.synthetic.main.fragment_register2.*


class Register2Fragment : Fragment(), View.OnClickListener {

    var get_name: String? = null
    var get_email: String? = null
    lateinit var navController: NavController
    lateinit var strusernname: String
    lateinit var stremail: String
    lateinit var loginViewModel: LoginViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register2, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        get_name = arguments?.getString("name")
        get_email = arguments?.getString("email")

        loginViewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        btnsubmit.setOnClickListener(this)
        tvStep2.setOnClickListener(this)
        tvInformasi.text =
            "hallo $get_name  untuk melanjutkan  register silahkan isi passsword dibawah"


    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btnsubmit -> {

                if (tvPassword1.text.toString().isEmpty()) {
                    tvPassword1.error = "password harus di isi"

                } else if (tvPassword2.text.toString().isEmpty()) {
                    tvPassword2.error = " confirmasi password harus di isi"
                } else  {
                    if (tvPassword1.text.toString().equals(tvPassword2.text.toString())) {
                        val bundle: Bundle = bundleOf(
                            "name" to get_name,
                            "email" to get_email,
                            "password" to tvPassword1.text.toString()
                        )

                        get_name?.let {
                            get_email?.let { it1 ->
                                loginViewModel.insertData(
                                    requireContext(),
                                    it,
                                    it1,
                                    tvPassword1.text.toString(),
                                    tvPassword2.text.toString()

                                    )
                            }
                        }
                        navController.navigate(R.id.resultFragment, bundle)

                    }
                    else{
                        Toast.makeText(context,"password tidak sama coba lagi",Toast.LENGTH_SHORT).show()
                    }
                }

            }
            R.id.tvStep2 -> navController.navigate(R.id.register1Fragment)

        }
    }

}