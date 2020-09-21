package com.example.register.Repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.register.local.DatabaseJadwal
import com.example.register.local.model.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class LoginRepository {

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

                val insertData = User( username, email,password,password1)
                databaseJadwal!!.userDao().insert(insertData)


            }

        }


        fun getLoginDetail(context: Context, username: String,password: String): LiveData<User>? {

            databaseJadwal = initializeDB(context)
            user = databaseJadwal!!.userDao().getLoginDetail(username,password)
            return user
        }


    }


}