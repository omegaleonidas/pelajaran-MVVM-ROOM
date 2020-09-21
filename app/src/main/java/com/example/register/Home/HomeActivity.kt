package com.example.register.Home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavAction
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.register.R
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {

    companion object {
        const val LOGIN_SESSION = "login_session"
    }
    var get_name: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        var nama = intent.getStringExtra(LOGIN_SESSION)

        tvname.text = " HELLO  $nama"



        val navController = Navigation.findNavController(this,R.id.nav_host_fragment)
        NavigationUI.setupWithNavController(buttonNavigation,navController)
    }
}