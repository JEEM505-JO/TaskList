package com.devjeem.tasklist.ui.addTask

import com.devjeem.tasklist.data.Tasking

data class StateAddTask(
    val addTask: () -> Unit = {},
    val title : String = "",
    val onChangeTitle : (String) -> Unit = {},
    val description : String = "",
    val onChangeDescription : (String) -> Unit = {},
    val success: Boolean = false,
    val error: Boolean = false,
    val loading: Boolean = false,
    val listTask: List<Tasking> = emptyList(),
    val updateTask: () -> Unit = {},
    val getDataFromId : (id : Int) -> Unit = {},
    val deleteTask: () -> Unit = {},
    val onTaskListNav: () -> Unit = {},
    val setTaskListNav: (() -> Unit) -> Unit = {},
    val modelData : Tasking = Tasking(),
    val showDialog : Boolean = false,
    val openDialog : () -> Unit = {},
    val onDismissDialog : () -> Unit = {},
    val checkTask: Boolean = false,
    val onChangeTask: (Boolean) -> Unit = {},
){
    fun validEmpty() : Boolean {
        return title.isNotEmpty() && description.isNotEmpty()
    }
}
