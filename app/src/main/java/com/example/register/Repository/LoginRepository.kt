package com.example.register.Repository

import android.annotation.SuppressLint
import android.content.AsyncQueryHandler
import android.content.Context
import android.database.DatabaseErrorHandler
import android.util.Log
import androidx.lifecycle.LiveData
import com.example.register.local.DatabaseJadwal
import com.example.register.local.model.Jadwal
import com.example.register.local.model.User
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginRepository(context: Context) {

    private var databaseConfig: DatabaseJadwal? = null

    init {
        databaseConfig = DatabaseJadwal.getInstance(context)
    }

    companion object {
        var databaseJadwal: DatabaseJadwal? = null
        var user: LiveData<User>? = null

        fun initializeDB(context: Context): DatabaseJadwal {
            return DatabaseJadwal.getInstance(context)!!
        }

        fun insertData(
            context: Context,
            username: String,
            email: String,
            password: String,
            password1: String

        ) {
            databaseJadwal = initializeDB(context)
            CoroutineScope(Dispatchers.IO).launch {

                val insertData = User(username, email, password, password1)
                databaseJadwal!!.userDao().insert(insertData)


            }

        }


        fun getLoginDetail(context: Context, username: String, password: String): LiveData<User>? {

            databaseJadwal = initializeDB(context)
            user = databaseJadwal!!.userDao().getLoginDetail(username, password)
            Log.d("TAG", "validasi login viewmodel $user")
            return user
        }

        fun getCeklogin(context: Context, username: String,email: String): LiveData<User>? {

            databaseJadwal = initializeDB(context)
            user = databaseJadwal!!.userDao().cekregister(username,email)
            Log.d("TAG", "validasi username viewmodel $user")
            return user
        }


    }

//
}




















