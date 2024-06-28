package com.devjeem.tasklist.ui.listTask

import com.devjeem.tasklist.data.Tasking

data class StateTask(
    val addTask: (Tasking) -> Unit = {},
    val success: Boolean = false,
    val error: Boolean = false,
    val onChangeTask: (Boolean,Tasking) -> Unit = { _, _ -> },
    val loading: Boolean = false,
    val listTask: List<Tasking> = emptyList(),
    val updateTask: (Tasking) -> Unit = {},
    val deleteTask: (Tasking) -> Unit = {},
    val onAddTaskNav: (id: String) -> Unit = {},
    val setAddTaskNav: ((id: String) -> Unit) -> Unit = {},
    val getTaskList : () -> Unit = {}
)
