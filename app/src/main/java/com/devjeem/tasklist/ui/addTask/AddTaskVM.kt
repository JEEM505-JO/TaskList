package com.devjeem.tasklist.ui.addTask

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.devjeem.tasklist.data.Tasking
import com.devjeem.tasklist.repository.TaskingRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddTaskVM @Inject constructor(private val taskingRepo: TaskingRepo) : ViewModel() {
    private var _state: MutableStateFlow<StateAddTask> =
        MutableStateFlow(
            StateAddTask(
                addTask = ::addTask,
                updateTask = ::updateTask,
                deleteTask = ::deleteTask,
                getDataFromId = ::getDataFromId,
                setTaskListNav = ::setTaskListNav,
                openDialog = ::openDialogFromDelete,
                onChangeTask = ::onChangeTask,
                onChangeTitle = ::onChangeTitle,
                onChangeDescription = ::onChangeDescription,
            )
        )


    val state: StateFlow<StateAddTask> = _state.asStateFlow()

    private fun addTask() {
        viewModelScope.launch {
            val title = _state.value.title
            val description = _state.value.description
            val status = _state.value.checkTask
            val value = if (status) 1 else 0
            taskingRepo.insertTask(
                Tasking(
                    title = title,
                    description = description,
                    status = value
                )
            )
            _state.update {
                it.copy(success = true)
            }
        }
    }

    private fun onChangeTitle(title: String) {
        viewModelScope.launch {
            _state.update {
                it.copy(title = title)
            }
        }
    }

    private fun onChangeDescription(descrip: String) {
        viewModelScope.launch {
            _state.update {
                it.copy(description = descrip)
            }
        }
    }

    private fun getDataFromId(id: Int) {
        viewModelScope.launch {
            val data = taskingRepo.getTask(id)
            if (data != null) {
                _state.update {
                    it.copy(modelData = data, title = data.title, description = data.description)
                }
            } else {
                _state.update {
                    it.copy(modelData = Tasking(title = "", description = ""))
                }
            }
        }
    }

    private fun onChangeTask(b: Boolean) {
        viewModelScope.launch {
            _state.update {
                it.copy(checkTask = b)
            }
        }
    }

    private fun updateTask() {
        viewModelScope.launch {
            val modelData = _state.value.modelData
            val title = _state.value.title
            val description = _state.value.description
            val status = _state.value.checkTask
            val value = if (status) 1 else 0

            taskingRepo.updateTasking(description = description, title = title, id = modelData.id.toInt())
            taskingRepo.updateTask(status = value, id = modelData.id.toInt())
            _state.update {
                it.copy(success = true)
            }
        }
    }

    private fun openDialogFromDelete() {
        viewModelScope.launch {
            _state.update {
                it.copy(showDialog = true)
            }
        }
    }

    private fun onDismissDialog() {
        viewModelScope.launch {
            _state.update {
                it.copy(showDialog = false)
            }
        }
    }


    private fun deleteTask() {
        viewModelScope.launch {
            val modelData = _state.value.modelData
            taskingRepo.deleteTask(modelData.id.toInt())
            _state.update {
                it.copy(showDialog = false)
            }
            delay(1200)
            _state.update {
                it.copy(success = true)
            }
        }
    }


    private fun setTaskListNav(function: () -> Unit) {
        viewModelScope.launch {
            _state.update {
                it.copy(onTaskListNav = function)
            }
        }
    }
}