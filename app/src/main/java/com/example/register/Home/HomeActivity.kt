package com.example.register.Home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavAction
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.register.Helper.SessonManager
import com.example.register.R
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        var sessonManager = SessonManager(this)

        var nama = sessonManager.username

                title = "hello $nama"

        val navController = Navigation.findNavController(this, R.id.nav_host_fragment)
        NavigationUI.setupWithNavController(buttonNavigation, navController)
    }
}