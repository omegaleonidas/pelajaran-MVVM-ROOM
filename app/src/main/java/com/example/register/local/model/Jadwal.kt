package com.example.register.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "jadwal")
data class Jadwal(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null,
    @ColumnInfo(name = "pelajaran")
    var pelajaran: String? = null,
    @ColumnInfo(name = "keterangan")
    var keterangan: String? = null,
    @ColumnInfo(name = "date")
    var date: String? = null
){



}