package com.example.register.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.register.Repository.JadwalRepository
import com.example.register.local.DatabaseJadwal
import com.example.register.local.model.Jadwal

class JadwalViewModel(application: Application) : AndroidViewModel(application) {

    private var databaseJadwal: DatabaseJadwal? = null
    val repository = JadwalRepository(application.applicationContext)

    var ShowJadwal = MutableLiveData<List<Jadwal>?>()
    var isError = MutableLiveData<Throwable>()
    var AddJadwal = MutableLiveData<Unit>()
    var UpdateJadwal = MutableLiveData<Unit>()
    var DeleteJadwal = MutableLiveData<Unit>()

    fun showJadwalView() {
        repository.showJadwal({
            ShowJadwal.value = it
        }, {
            isError.value = it
        })
    }

    fun addJadwalView(item: Jadwal) {
        repository.addDataJadwal(item, {
            AddJadwal.value = it
        }, {
            isError.value = it
        })
    }

    fun updateJadwalView(item: Jadwal) {
        repository.updateJadwal(item, {
            UpdateJadwal.value = it
        }, {
            isError.value = it
        })
    }

    fun deleteJadwalView(item: Jadwal) {
        repository.deleteJadwal(item, {
            DeleteJadwal.value = it
        }, {
            isError.value = it
        })
    }


}