package com.example.register.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.register.local.model.Jadwal
import com.example.register.local.model.User

@Database(entities = arrayOf(Jadwal::class,User::class), version = 1)
abstract class DatabaseJadwal : RoomDatabase() {

    abstract fun jadwalDao(): DaoJadwal
    abstract fun userDao():DaoUser

    companion object {
        private var INSTANCE: DatabaseJadwal? = null
        fun getInstance(context: Context): DatabaseJadwal? {

            if (INSTANCE == null) {
                synchronized(DatabaseJadwal::class) {
                    INSTANCE =
                        Room.databaseBuilder(
                            context.applicationContext,
                            DatabaseJadwal::class.java,
                            "dbjadwal1.db"
                        ).build()
                }
            }
            return INSTANCE
        }

    }


}