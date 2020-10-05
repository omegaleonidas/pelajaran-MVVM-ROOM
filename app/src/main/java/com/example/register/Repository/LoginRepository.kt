package com.example.register.Repository

import android.annotation.SuppressLint
import android.content.Context
import com.example.register.Helper.SessonManager
import com.example.register.local.DatabaseJadwal
import com.example.register.local.model.User
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

class LoginRepository(context: Context) {

    private var databaseConfig: DatabaseJadwal? = null
    var sessonManager = SessonManager(context)

    init {
        databaseConfig = DatabaseJadwal.getInstance(context)
    }


    @SuppressLint("CheckResult")
    fun detailUser(

        username: String,
        password: String,
        responHandler: (User) -> Unit,
        errorHandler: (Throwable) -> Unit
    ) {
        io.reactivex.rxjava3.core.Observable.fromCallable {
            databaseConfig?.userDao()?.getLoginDetail(username, password)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it?.let { it1 ->
                    responHandler(it1)
                    sessonManager.username = it1.username
                    sessonManager.login = true
                }
            }, {
                errorHandler(it)
            })

    }

    @SuppressLint("CheckResult")
    fun cekLoginRegister(

        username: String,
        email: String,
        responHandler: (User) -> Unit,
        errorHandler: (Throwable) -> Unit
    ) {
        io.reactivex.rxjava3.core.Observable.fromCallable {
            databaseConfig?.userDao()?.getcekregister(username, email)
        }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it?.let { it1 ->
                    responHandler(it1)

                }
            }, {
                errorHandler(it)
            })

    }


    @SuppressLint("CheckResult")
    fun showUser(responHandler: (List<User>?) -> Unit, errorHandler: (Throwable) -> Unit) {
        Observable.fromCallable { databaseConfig?.userDao()?.getData() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it?.let { it1 -> responHandler(it1) }
            }, {
                errorHandler(it)
            })
    }

    @SuppressLint("CheckResult")
    fun insertUser(
        item: User, responHandler: (Unit?) -> Unit, errorHandler: (Throwable) -> Unit
    ) {
        Observable.fromCallable() {
            databaseConfig?.userDao()?.insert(item!!)
        }.subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responHandler(it)
            }, {
                errorHandler(it)
            })


    }

}























