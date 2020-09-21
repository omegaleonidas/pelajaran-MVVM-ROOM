package com.example.register.ViewModel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.register.Repository.LoginRepository
import com.example.register.local.model.User

class LoginViewModel: ViewModel()  {
    var liveDataLogin: LiveData<User>? = null

    fun insertData(context: Context, username: String, email:String,password:String,password1:String) {
        LoginRepository.insertData(context,username,email,password,password1)
    }


    fun getLoginDetails(context: Context, username: String,password: String) : LiveData<User>? {
        liveDataLogin = LoginRepository.getLoginDetail(context,username,password)
        return liveDataLogin
    }


}