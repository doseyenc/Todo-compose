package com.doseyenc.todo.ui.screens.task

import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import com.doseyenc.todo.data.models.ToDoTask
import com.doseyenc.todo.util.Action

@Composable
fun TaskScreen(
    navigateToListScreen: (Action) -> Unit,
    selectedTask: ToDoTask?
) {
    Scaffold(
        topBar = {
            TaskAppBar(
                navigateToListScreen = navigateToListScreen,
                selectedTask = selectedTask
            )
        },
        content = {}
    )
}