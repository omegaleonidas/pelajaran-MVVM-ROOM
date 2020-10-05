package com.example.register.Helper

import android.content.Context
import android.content.SharedPreferences

class SessonManager(var context: Context){

    var pref: SharedPreferences? = null
    var editor: SharedPreferences.Editor? = null
    var PREF_NAME = "LOGIN"

    var ISLOGIN = "isLogin"
    var USERNAME = "username"
    var PASSWORD = "password"

    init {
        pref = context.getSharedPreferences(PREF_NAME, 0)
        editor = pref?.edit()
    }

    var login: Boolean?
        get() = pref?.getBoolean(ISLOGIN, false)
        set(login) {
            editor?.putBoolean(ISLOGIN, true)
            editor?.commit()
        }

    var username: String?
        get() = pref?.getString(USERNAME, "")
        set(username) {
            editor?.putString(USERNAME, username)
            editor?.commit()
        }

    var password: String?
        get() = pref?.getString(PASSWORD, "")
        set(password) {
            editor?.putString(PASSWORD, password)
            editor?.commit()
        }

    fun logOut() {
        editor?.clear()
        editor?.commit()
    }

}