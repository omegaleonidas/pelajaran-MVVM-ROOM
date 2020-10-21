package com.example.register.ViewModel

import android.app.Application
import android.content.Context
import android.widget.Toast
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.register.Repository.JadwalRepository
import com.example.register.local.DatabaseJadwal
import com.example.register.local.model.Jadwal
import com.example.register.local.model.User

class JadwalViewModel(application: Application) : AndroidViewModel(application) {

    private var databaseJadwal: DatabaseJadwal? = null
    val repository = JadwalRepository(application.applicationContext)
    var context: Context = application

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

    fun addJadwalView(item: Jadwal){

        if (item.pelajaran?.isEmpty()!! ) {
            Toast.makeText(context, "data harus di isi ya ", Toast.LENGTH_SHORT).show()
        }  else if (item.keterangan?.isEmpty()!!){
            Toast.makeText(context, "data harus di isi ya ", Toast.LENGTH_SHORT).show()
        }  else {
            repository.addDataJadwal(item, {

                AddJadwal.value = it
            }, {
                isError.value = it
            })
        }

    }

    fun updateJadwalView(item: Jadwal) {
        if (item.pelajaran?.isEmpty()!! ) {
            Toast.makeText(context, "data harus di isi ya ", Toast.LENGTH_SHORT).show()
        }  else if (item.keterangan?.isEmpty()!!){
            Toast.makeText(context, "data harus di isi ya ", Toast.LENGTH_SHORT).show()
        }  else {

            repository.updateJadwal(item, {
                UpdateJadwal.value = it
            }, {
                isError.value = it
            })
        }
    }

    fun deleteJadwalView(item: Jadwal) {
        repository.deleteJadwal(item, {
            DeleteJadwal.value = it
        }, {
            isError.value = it
        })
    }


}