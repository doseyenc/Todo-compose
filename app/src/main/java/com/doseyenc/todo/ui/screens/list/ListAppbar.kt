package com.doseyenc.todo.ui.screens.list

import androidx.compose.material3.ColorScheme
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.doseyenc.todo.ui.theme.topAppBarBackgroundColor
import com.doseyenc.todo.ui.theme.topAppBarContentColor

@Composable
fun ListAppbar() {
    DefaultListAppbar()
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DefaultListAppbar() {
    TopAppBar(
        title = {
            Text(
                text = "Tasks",
                color = MaterialTheme.colorScheme.topAppBarContentColor
            )
        },

        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.topAppBarBackgroundColor
        )
    )
}

@Composable
@Preview
fun ListAppbarPreview() {
    DefaultListAppbar()
}
