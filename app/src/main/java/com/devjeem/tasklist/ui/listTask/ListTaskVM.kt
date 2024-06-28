package com.devjeem.tasklist.ui.listTask

import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devjeem.tasklist.data.Tasking
import com.devjeem.tasklist.repository.TaskingRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ListTaskVM @Inject constructor(private val taskingRepo: TaskingRepo) : ViewModel() {

    private var _state: MutableStateFlow<StateTask> =
        MutableStateFlow(
            StateTask(
                addTask = ::addTask,
                deleteTask = ::deleteTask,
                setAddTaskNav = ::setAddTaskNav,
                onChangeTask = ::onChangeTask,
                getTaskList = ::getTaskList
            )
        )



    val state: StateFlow<StateTask> = _state.asStateFlow()

    init {
        viewModelScope.launch {
            getTaskList()
        }
    }

    private fun addTask(tasking: Tasking) {
        viewModelScope.launch {
            taskingRepo.insertTask(tasking)
        }
    }

    private fun onChangeTask(b: Boolean, tasking: Tasking) {
        viewModelScope.launch {
            val status = if (b) 1 else 0
            taskingRepo.updateTask(status, tasking.id.toInt())
            getTaskList()
        }
    }

    private fun setAddTaskNav(onNavAddTask : (id : String) -> Unit) {
        viewModelScope.launch {
            _state.update {
                it.copy(onAddTaskNav = onNavAddTask)
            }
        }
    }


    private fun deleteTask(tasking: Tasking) {
        viewModelScope.launch {
            taskingRepo.deleteTask(tasking.id.toInt())
            getTaskList()
        }
    }

    private fun getTaskList() {
        viewModelScope.launch {
            val list = arrayListOf<Tasking>()
            taskingRepo.getAllTasks().let { listTask ->
                if (listTask.isEmpty()) {
                    list.add(Tasking(0, "No hay tareas", "", status = 0))
                } else {
                  list.addAll(listTask)
                }
                _state.update {
                    it.copy(listTask = list)
                }
            }
        }

    }


}