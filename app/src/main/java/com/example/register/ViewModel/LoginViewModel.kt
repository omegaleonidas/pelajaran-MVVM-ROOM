package com.example.register.ViewModel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.register.Repository.JadwalRepository
import com.example.register.Repository.LoginRepository
import com.example.register.local.DatabaseJadwal
import com.example.register.local.model.Jadwal
import com.example.register.local.model.User

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    var liveDataLogin: LiveData<User>? = null

    fun insertData(context: Context, username: String, email:String,password:String,password1:String) {
        LoginRepository.insertData(context,username,email,password,password1)
    }


    fun getLoginDetails(context: Context, username: String,password: String) : LiveData<User>? {
        liveDataLogin = LoginRepository.getLoginDetail(context,username,password)
        return liveDataLogin
    }
    fun getCekRegister(context: Context, username: String,email: String) : LiveData<User>? {
        liveDataLogin = LoginRepository.getCeklogin(context,username,email)
        return liveDataLogin
    }

//


}