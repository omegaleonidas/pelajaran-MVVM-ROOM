package com.example.register.local

import androidx.room.*
import com.example.register.local.model.User

@Dao
interface DaoUser {

    @Query("SELECT * FROM user WHERE username =:username AND password=:password ")
    fun getLoginDetail(username: String?,password:String?) : User

    @Query("SELECT * FROM  user WHERE username =:username OR email =:email")
    fun getcekregister(username: String?,email:String?): User

    @Query("SELECT * FROM  user ")
    fun getData(): List<User>

    @Insert
    fun insert(user: User)



    @Update
    fun update(user: User)

    @Delete
    fun delete(user: User)
}