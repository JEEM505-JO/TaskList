package com.devjeem.tasklist.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.devjeem.tasklist.data.Tasking
import com.devjeem.tasklist.database.dao.TaskingDao

@Database(entities = [Tasking::class], version = 1, exportSchema = false)
abstract class MainDataBase : RoomDatabase() {
    abstract fun taskingDao(): TaskingDao
}