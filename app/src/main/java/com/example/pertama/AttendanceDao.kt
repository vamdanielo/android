package com.example.pertama

import androidx.room.Dao
import androidx.room.Insert

@Dao

interface AttendanceDao {

    @Insert
    suspend fun insertAttendance(attendanceDao: AttendanceEntity): Long
}