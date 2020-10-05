package com.example.register.ViewModel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.register.Helper.SessonManager
import com.example.register.Repository.LoginRepository
import com.example.register.local.DatabaseJadwal
import com.example.register.local.model.User

class LoginViewModel(application: Application) : AndroidViewModel(application) {
    var liveDataLogin: LiveData<User>? = null


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

        repository.detailUser(username, password, {
            Log.d("TAG", "lloginValidation: ViewModel getDataEmail  $it.")
            detailLogin.value = it

        }, {
            isError.value = it
        })

        return user
    }

    fun getCekRegister(username: String, email: String): LiveData<User> {

        val user = MutableLiveData<User>()

        repository.cekLoginRegister(username, email, {
            Log.d("TAG", "lloginValidation: ViewModel getDataEmail  $it.")
            detailLCekLogin.value = it
        }, {
            isError.value = it
        })

        return user
    }

//


}