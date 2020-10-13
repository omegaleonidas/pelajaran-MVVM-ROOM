package com.example.register.local

import androidx.room.*
import com.example.register.local.model.Jadwal

@Dao
interface DaoJadwal {

    @Query("SELECT * FROM jadwal ")
    fun getAll(): List<Jadwal>

    @Insert
    fun insert(jadwal: Jadwal)

    @Update
    fun update(jadwal: Jadwal)

    @Delete
    fun delete(jadwal: Jadwal)


}