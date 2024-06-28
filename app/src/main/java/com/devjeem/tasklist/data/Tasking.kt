package com.devjeem.tasklist.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "TASKING")
data class Tasking(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "ID")
    val id: Long = 0L,
    @ColumnInfo(name = "TITLE")
    val title: String = "",
    @ColumnInfo(name = "DESCRIPTION")
    val description: String = "",
    @ColumnInfo(name = "STATUS")
    var status: Int = 1
)
