package com.devjeem.tasklist.ui.addTask

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devjeem.tasklist.R
import com.devjeem.tasklist.ui.dialog.DialogDelete

@Composable
fun AddTaskScreen(stateAddTask: StateAddTask) {
    LaunchedEffect(key1 = stateAddTask.success) {
        if (stateAddTask.success) {
            stateAddTask.onTaskListNav()
        }
    }
    if (stateAddTask.showDialog) {
        DialogDelete(
            onCancel = { stateAddTask.onDismissDialog() },
            onAccept = { stateAddTask.deleteTask() },
            onDismiss = { stateAddTask.onDismissDialog() }
        )
    }

    AddTaskContent(stateAddTask = stateAddTask)

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTaskContent(stateAddTask: StateAddTask) {
    Scaffold (
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = colorResource(id = R.color.iconColor),
                    titleContentColor = colorResource(id = R.color.black),
                ),
                title = {
                    Text(
                        text = stringResource(id = R.string.AddTask),
                        color = colorResource(id = R.color.white),
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { stateAddTask.onTaskListNav() }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "back",
                            tint = Color.White
                        )
                    }
                })
        }) { paddingValues ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.TopCenter)
            ) {
                if (stateAddTask.modelData.title.isEmpty()) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(text = "Añadir una nueva tarea")
                    }
                } else {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center
                    ) {
                        Text(text = "Editar tarea")
                    }
                }

                OutlinedTextField(
                    value = stateAddTask.title,
                    onValueChange = stateAddTask.onChangeTitle,
                    label = {
                        Text(
                            text = stringResource(id = R.string.title_add_task),
                            fontSize = 15.sp,
                        )
                    },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp, end = 10.dp, start = 10.dp),
                    shape = ShapeDefaults.Medium,
                    singleLine = true,
                    keyboardOptions = KeyboardOptions.Default.copy(
                        autoCorrect = true, keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Next
                    )
                )
                OutlinedTextField(
                    value = stateAddTask.description,
                    onValueChange = stateAddTask.onChangeDescription,
                    label = {
                        Text(
                            text = stringResource(id = R.string.add_description),
                            fontSize = 15.sp,
                        )
                    }, shape = ShapeDefaults.Medium,
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp, end = 10.dp, start = 10.dp),
                    keyboardOptions = KeyboardOptions.Default.copy(
                        autoCorrect = true, keyboardType = KeyboardType.Text,
                        imeAction = ImeAction.Done
                    )
                )
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .height(43.dp)
                ) {
                    Checkbox(
                        checked = stateAddTask.checkTask,
                        onCheckedChange = {stateAddTask.onChangeTask(it)}
                    )
                    AnimatedVisibility(visible = stateAddTask.checkTask) {
                        Text(
                            text = stringResource(id = R.string.task_done),
                            color = colorResource(id = R.color.black),
                            fontSize = 18.sp,
                        )
                    }
                    AnimatedVisibility(visible = !stateAddTask.checkTask) {
                        Text(
                            text = stringResource(id = R.string.task_pending),
                            color = colorResource(id = R.color.black),
                            fontSize = 18.sp,
                        )
                    }
                }
                if (stateAddTask.modelData.title.isEmpty()) {
                    Row(
                        modifier = Modifier
                            .padding(20.dp)
                    ) {
                        Button(
                            onClick = { stateAddTask.addTask() },
                            enabled = stateAddTask.validEmpty()
                        ) {
                            Text(text = stringResource(id = R.string.save))
                        }
                    }
                } else {
                    Row(
                        modifier = Modifier
                            .padding(20.dp)
                    ) {
                        Button(
                            onClick = { stateAddTask.updateTask() },
                            enabled = stateAddTask.validEmpty()
                        ) {
                            Text(text = stringResource(id = R.string.update))
                        }
                    }
                }
            }



        }
    }
}
