package com.example.register.Repository

import android.annotation.SuppressLint
import android.content.Context
import android.database.Observable
import com.example.register.local.DatabaseJadwal
import com.example.register.local.model.Jadwal
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Completable.fromCallable
import io.reactivex.rxjava3.core.Flowable.fromCallable
import io.reactivex.rxjava3.core.Maybe.fromCallable
import io.reactivex.rxjava3.core.Single.fromCallable
import io.reactivex.rxjava3.schedulers.Schedulers

class JadwalRepository(context: Context) {

    private var databaseConfig : DatabaseJadwal? = null

    init {
        databaseConfig = DatabaseJadwal.getInstance(context)
    }


    @SuppressLint("CheckResult")
    fun showJadwal(responHandler: (List<Jadwal>?) -> Unit, errorHandler: (Throwable) -> Unit) {
        io.reactivex.rxjava3.core.Observable.fromCallable() { databaseConfig?.jadwalDao()?.getAll() }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                it?.let { it1 -> responHandler(it1) }
            }, {
                errorHandler(it)
            })
    }

    @SuppressLint("CheckResult")
    fun addDataJadwal(item: Jadwal, responHandler: (Unit?) -> Unit, errorHandler: (Throwable) -> Unit) {
        io.reactivex.rxjava3.core.Observable.fromCallable { databaseConfig?.jadwalDao()?.insert(item!!) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responHandler(it)
            }, {
                errorHandler(it)
            })
    }

    @SuppressLint("CheckResult")
    fun updateJadwal(item: Jadwal, responHandler: (Unit?) -> Unit, errorHandler: (Throwable) -> Unit) {
        io.reactivex.rxjava3.core.Observable.fromCallable { databaseConfig?.jadwalDao()?.update(item!!) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responHandler(it)
            }, {
                errorHandler(it)
            })
    }

    @SuppressLint("CheckResult")
    fun deleteJadwal(item: Jadwal, responHandler: (Unit?) -> Unit, errorHandler: (Throwable) -> Unit) {
        io.reactivex.rxjava3.core.Observable.fromCallable { databaseConfig?.jadwalDao()?.delete(item!!) }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                responHandler(it)
            }, {
                errorHandler(it)
            })
    }



}

