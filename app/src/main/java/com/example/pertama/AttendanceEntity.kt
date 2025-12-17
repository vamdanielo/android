package com.example.pertama

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey


@Entity(
    tableName = "attendance",
    foreignKeys = [
        ForeignKey(
            entity = UserEntity::class,
            parentColumns = ["id"],
            childColumns =  ["user_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)

class AttendanceEntity{
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0
}