package com.example.register.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "user")

data class User(

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    var id: Int? = null,

    @ColumnInfo(name = "username")
    var username: String? = null,

    @ColumnInfo(name = "email")
    var email: String? = null,

    @ColumnInfo(name = "password")
    var password: String? = null,

    @ColumnInfo(name = "password1")
    var password1: String? = null

)