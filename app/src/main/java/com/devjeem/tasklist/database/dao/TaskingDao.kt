package com.devjeem.tasklist.database.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.devjeem.tasklist.data.Tasking
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskingDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(vararg tasking: Tasking)

    @Query("SELECT * FROM TASKING")
    suspend fun getAllTasks(): List<Tasking>

    @Query("UPDATE TASKING SET STATUS = :status WHERE ID = :id")
    suspend fun updateTask(status: Int, id: Int)

    @Query("UPDATE TASKING SET description = :description, title = :title WHERE ID = :id")
    suspend fun updateTasking(description: String, title: String, id: Int)


    @Query("DELETE FROM TASKING WHERE ID = :id")
    suspend fun deleteTask(id: Int)


    @Query("SELECT * FROM TASKING WHERE ID = :id")
    suspend fun getTaskingById(id: Int): Tasking


}