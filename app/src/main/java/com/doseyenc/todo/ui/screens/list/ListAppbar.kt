package com.doseyenc.todo.ui.screens.list

import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.doseyenc.todo.R
import com.doseyenc.todo.components.PriorityItem
import com.doseyenc.todo.data.models.Priority
import com.doseyenc.todo.ui.theme.LARGE_PADDING
import com.doseyenc.todo.ui.theme.Typography
import com.doseyenc.todo.ui.theme.topAppBarBackgroundColor
import com.doseyenc.todo.ui.theme.topAppBarContentColor

@Composable
fun ListAppbar() {
    DefaultListAppbar()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultListAppbar(
    onSearchClicked: () -> Unit = {},
    onSortClicked: (Priority) -> Unit = {},
    onDeleteClicked: () -> Unit = {}
) {
    TopAppBar(
        title = {
            Text(
                text = "Tasks",
                color = MaterialTheme.colorScheme.topAppBarContentColor
            )
        },
        actions = {
            ListAppbarActions(
                onSearchClicked = onSearchClicked,
                onSortClicked = onSortClicked,
                onDeleteClicked = onDeleteClicked
            )
        },

        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.topAppBarBackgroundColor
        )
    )
}

@Composable
fun ListAppbarActions(
    onSearchClicked: () -> Unit,
    onSortClicked: (Priority) -> Unit,
    onDeleteClicked: () -> Unit
) {
    SearchAction(onSearchClicked)
    SortAction(onSortClicked)
    DeleteAllAction (onDeleteClicked )
}

@Composable
fun SearchAction(
    onSearchClicked: () -> Unit
) {
    IconButton(onClick = onSearchClicked) {
        Icon(
            imageVector = Icons.Filled.Search,
            contentDescription = stringResource(R.string.searchIcon),
            tint = MaterialTheme.colorScheme.topAppBarContentColor
        )
    }
}

@Composable
fun SortAction(
    onSortClicked: (Priority) -> Unit
) {

    var expanded by remember { mutableStateOf(false) }

    IconButton(
        onClick = { expanded = true }
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_filter),
            contentDescription = stringResource(R.string.filterIcon),
            tint = MaterialTheme.colorScheme.topAppBarContentColor
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                onClick = {
                    expanded = false
                    onSortClicked(Priority.LOW)
                },
                text = { PriorityItem(priority = Priority.LOW) }
            )

            DropdownMenuItem(
                onClick = {
                    expanded = false
                    onSortClicked(Priority.HIGH)
                },
                text = { PriorityItem(priority = Priority.HIGH) }
            )

            DropdownMenuItem(
                onClick = {
                    expanded = false
                    onSortClicked(Priority.NONE)
                },
                text = { PriorityItem(priority = Priority.NONE) }
            )
        }
    }
}

@Composable
fun DeleteAllAction(
    onDeleteClicked: () -> Unit
) {
    var expanded by remember { mutableStateOf(false) }

    IconButton(
        onClick = { expanded = true }
    ) {
        Icon(
            painter = painterResource(R.drawable.ic_vertical_menu),
            contentDescription = stringResource(R.string.deleteAll),
            tint = MaterialTheme.colorScheme.topAppBarContentColor
        )

        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                onClick = {
                    expanded = false
                    onDeleteClicked()
                },
                text = {
                    Text(
                        modifier = Modifier.padding(start = LARGE_PADDING),
                        text = stringResource(R.string.deleteAll),
                        style = Typography.labelLarge
                    )
                }
            )
        }
    }
}

@Composable
@Preview
fun ListAppbarPreview() {
    DefaultListAppbar(
        onSearchClicked = {},
        onSortClicked = {},
        onDeleteClicked = {}
    )
}
