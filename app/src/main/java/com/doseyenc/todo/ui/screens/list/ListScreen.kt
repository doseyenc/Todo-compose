package com.doseyenc.todo.ui.screens.list

import android.annotation.SuppressLint
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import com.doseyenc.todo.R
import com.doseyenc.todo.ui.theme.fabBackgroundColor
import com.doseyenc.todo.ui.viewmodels.SharedViewModel
import com.doseyenc.todo.util.Action
import com.doseyenc.todo.util.SearchAppBarState

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ListScreen(
    navigateToTaskScreen: (taskId: Int) -> Unit,
    sharedViewModel: SharedViewModel
) {
    LaunchedEffect(key1 = true) {
        sharedViewModel.getAllTasks()
    }

    val allTasks by sharedViewModel.allTasks.collectAsState()
    val searchedTasks by sharedViewModel.searchedTasks.collectAsState()
    val searchAppBarState: SearchAppBarState by sharedViewModel.searchAppBarState
    val searchTextState by sharedViewModel.searchTextState

    val action by sharedViewModel.action
    val snackBarHostState = remember { SnackbarHostState() }

    LaunchedEffect(key1 = action) {
        if (action != Action.NO_ACTION) {
            sharedViewModel.handleDatabaseActions(action)
            val snackBarResult = snackBarHostState.showSnackbar(
                message = setMessage(action = action, taskTitle = sharedViewModel.title.value),
                actionLabel = setActionLabel(action),
                duration = SnackbarDuration.Long
            )
            undoDeleteTask(
                action = action,
                snackBarResult = snackBarResult,
                onUndoClicked = {
                    sharedViewModel.action.value = it
                }
            )
            if (sharedViewModel.action.value == Action.UNDO) {
                return@LaunchedEffect
            } else {
                sharedViewModel.action.value = Action.NO_ACTION
            }
        }
    }

    Scaffold(
        snackbarHost = { SnackbarHost(hostState = snackBarHostState) },
        content = {
            ListContent(
                allTasks = allTasks,
                navigateToTaskScreen = navigateToTaskScreen,
                searchAppBarState = searchAppBarState,
                searchedTasks = searchedTasks,
            )
        },
        topBar = {
            ListAppbar(
                sharedViewModel = sharedViewModel,
                searchAppBarState = searchAppBarState,
                searchTextState = searchTextState
            )
        },
        floatingActionButton = {
            ListFab(onFabClicked = navigateToTaskScreen)
        }
    )
}

@Composable
fun ListFab(
    onFabClicked: (taskId: Int) -> Unit
) {
    FloatingActionButton(
        onClick = {
            onFabClicked(-1)
        },
        shape = CircleShape,
        containerColor = MaterialTheme.colorScheme.fabBackgroundColor
    ) {
        Icon(
            imageVector = Icons.Filled.Add,
            contentDescription = stringResource(id = R.string.add_button),
            tint = Color.White
        )
    }
}

private fun setActionLabel(action: Action): String {
    return if (action.name == "DELETE") {
        "UNDO"
    } else {
        "OK"
    }
}

fun setMessage(action: Action, taskTitle: String): String {
    return if (action == Action.DELETE_ALL) {
        "All tasks removed"
    } else {
        "${action.name}: $taskTitle"
    }
}

private fun undoDeleteTask(
    action: Action,
    snackBarResult: SnackbarResult,
    onUndoClicked: (Action) -> Unit
) {
    if (snackBarResult == SnackbarResult.ActionPerformed && action == Action.DELETE) {
        onUndoClicked(Action.UNDO)
    }
}
