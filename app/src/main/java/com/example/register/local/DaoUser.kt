package com.example.register.local

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.register.local.model.User
import io.reactivex.rxjava3.core.Completable

@Dao
interface DaoUser {

    @Query("SELECT * FROM user WHERE username =:username AND password=:password ")
    fun getLoginDetail(username: String?,password:String?) : LiveData<User>

    @Query("SELECT * FROM  user WHERE username =:username OR email =:email")
    fun cekregister(username: String?,email:String?): LiveData<User>
    @Insert
    fun insert(user: User)

    @Update
    fun update(user: User)

    @Delete
    fun delete(user: User)
}