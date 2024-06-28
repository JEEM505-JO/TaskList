package com.devjeem.tasklist.repository

import com.devjeem.tasklist.data.Tasking
import com.devjeem.tasklist.database.dao.TaskingDao
import javax.inject.Inject

class TaskingRepo @Inject constructor(private val taskingDao: TaskingDao) {

    suspend fun insertTask(tasking: Tasking) = taskingDao.insertTask(tasking)

    suspend fun getAllTasks() = taskingDao.getAllTasks()

    suspend fun updateTask(status: Int, id: Int) = taskingDao.updateTask(status, id)

    suspend fun deleteTask(id: Int) = taskingDao.deleteTask(id)

    suspend fun getTask(id: Int) = taskingDao.getTaskingById(id)

    suspend fun updateTasking(description: String, title: String, id: Int) = taskingDao.updateTasking(description, title, id)


}