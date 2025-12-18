package com.example.pertama


import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity (
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    @ColumnInfo(name = "first_name")
    val namaDepan: String,

    @ColumnInfo(name = "last_nama")
    val namaBelakang: String,
    val username : String,
    val email : String,
    val password : String


)