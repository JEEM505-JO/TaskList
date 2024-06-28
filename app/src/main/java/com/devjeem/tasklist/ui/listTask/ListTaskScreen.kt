package com.devjeem.tasklist.ui.listTask

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.lazy.items
import com.devjeem.tasklist.data.Tasking
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.devjeem.tasklist.R

@Composable
fun ListTaskScreen(stateTask: StateTask) {
    stateTask.getTaskList()
    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = { stateTask.onAddTaskNav("0") }) {
                Text("+")
            }
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 20.dp, top = 10.dp, end = 20.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.welcome),
                    fontSize = 30.sp,
                )
                Spacer(modifier = Modifier.size(10.dp))
                Text(
                    text = stringResource(id = R.string.yourTask),
                    fontSize = 20.sp,
                )
            }
            LazyColumn {
                items(stateTask.listTask) { note ->
                    NoteItem(tasking = note, stateTask = stateTask)
                }
            }
        }
    }
}

@Composable
fun NoteItem(tasking: Tasking, stateTask: StateTask) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(10.dp)
            .clickable(onClick = { stateTask.onAddTaskNav(tasking.id.toString()) })
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .padding(16.dp)
                    .align(Alignment.Center)
            ) {
                Text(text = tasking.title, style = MaterialTheme.typography.titleMedium)
                Spacer(modifier = Modifier.height(8.dp))
                Text(text = tasking.description, style = MaterialTheme.typography.bodyMedium)
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .height(43.dp)
                ) {
                    Checkbox(
                        checked = tasking.status == 1,
                        onCheckedChange = { stateTask.onChangeTask(it,tasking) }
                    )
                    AnimatedVisibility(visible = tasking.status == 1) {
                        Text(
                            text = stringResource(id = R.string.task_done),
                            color = colorResource(id = R.color.black),
                            fontSize = 18.sp,
                        )
                    }
                    AnimatedVisibility(visible = tasking.status == 0) {
                        Text(
                            text = stringResource(id = R.string.task_pending),
                            color = colorResource(id = R.color.black),
                            fontSize = 18.sp,
                        )
                    }
                }
            }
            IconButton(
                onClick = { stateTask.deleteTask(tasking) },
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(10.dp)
            ) {
                Icon(imageVector = Icons.Filled.Delete, contentDescription = "delete")
            }
        }

    }
}
