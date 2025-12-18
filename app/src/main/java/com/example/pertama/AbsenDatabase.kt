package com.example.pertama

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.room.migration.Migration
import com.example.myapplication.MIGRATION_2_3

@Database(entities = [UserEntity::class,
        AttendanceEntity::class], version = 3, exportSchema = false)
abstract class AbsenDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun attendanceDao(): AttendanceDao

    companion object {
        @Volatile
        private var INSTANCE: AbsenDatabase? = null



        fun getDatabase(context: Context): AbsenDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AbsenDatabase::class.java,
                    "aplikasiabsen"
                ).addMigrations(MIGRATION_2_3)
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}