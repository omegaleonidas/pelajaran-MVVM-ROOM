package com.example.register.View

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.example.register.Helper.SessonManager
import com.example.register.Home.HomeActivity
import com.example.register.R

class flashscreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flashscreen)
        var sessonLogin = SessonManager(this)

        Handler().postDelayed(Runnable {

            if (sessonLogin.login ?: true) {

                startActivity(Intent(this, HomeActivity::class.java))
                finish()
            } else {
                startActivity(Intent(this, MainActivity::class.java))
                    finish()
            }

        }, 5000)
        title = ""


    }

    override fun onDestroy() {
        super.onDestroy()
        finish()
    }
}