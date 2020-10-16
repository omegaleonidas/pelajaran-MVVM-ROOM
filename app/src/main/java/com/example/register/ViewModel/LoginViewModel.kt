package com.example.register.ViewModel

import android.app.Application
import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.register.Helper.SessonManager
import com.example.register.Repository.LoginRepository
import com.example.register.local.DatabaseJadwal
import com.example.register.local.model.User

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    var liveDataLogin: LiveData<User>? = null

    val context: Context = application
    private var databaseJadwal: DatabaseJadwal? = null
    val repository = LoginRepository(application.applicationContext)
    var responShowUser = MutableLiveData<List<User>?>()
    var detailLogin = MutableLiveData<User>()

    var isError = MutableLiveData<Throwable>()
    var AddUser = MutableLiveData<Unit>()
    var detailLCekLogin = MutableLiveData<User>()


    fun showUserView() {
        repository.showUser({
            responShowUser.value = it
        }, {
            isError.value = it
        })
    }

    fun addUserView(item: User) {
        repository.insertUser(item, {
            AddUser.value = it
        }, {
            isError.value = it
        })
    }


    fun getLogin(username: String, password: String): LiveData<User> {

        val user = MutableLiveData<User>()
        if (username.isEmpty()) {
            Toast.makeText(context, "isi username ", Toast.LENGTH_SHORT).show()
        } else if (password.isEmpty()) {
            Toast.makeText(context, "isi password", Toast.LENGTH_SHORT).show()
        } else if (password.length < 6){

            Toast.makeText(context,"password kurang dari 6 ",Toast.LENGTH_SHORT).show()
        }else {


            repository.detailUser(username, password, {
                Log.d("TAG", "lloginValidation: ViewModel getDataEmail  $it.")
                detailLogin.value = it

            }, {
                isError.value = it
            })


        }
        return user

    }

    fun getCekRegister(username: String, email: String): LiveData<User> {

        val user = MutableLiveData<User>()

        if (username.isEmpty()) {
            Toast.makeText(context, "isi username ", Toast.LENGTH_SHORT).show()
        } else if (email.isEmpty()) {
            Toast.makeText(context, "isi email", Toast.LENGTH_SHORT).show()
        } else {
            repository.cekLoginRegister(username, email, {
                Log.d("TAG", "lloginValidation: ViewModel getDataEmail  $it.")
                detailLCekLogin.value = it
            }, {
                isError.value = it
            })

        }

        return user
    }
}





//


