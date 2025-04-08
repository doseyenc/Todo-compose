package com.doseyenc.todo.components

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.wear.compose.material.ContentAlpha
import com.doseyenc.todo.R
import com.doseyenc.todo.data.models.Priority
import com.doseyenc.todo.ui.theme.PRIORITY_DROP_DOWN_HEIGHT
import com.doseyenc.todo.ui.theme.PRIORITY_INDICATOR_SIZE

@Composable
fun PriorityDropDown(
    priority: Priority,
    onPrioritySelected: (Priority) -> Unit
) {
    var expanded by remember {
        mutableStateOf(false)
    }

    val angle: Float by animateFloatAsState(
        targetValue = if (expanded) 180f else 0f
    )

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(PRIORITY_DROP_DOWN_HEIGHT)
            .clickable { expanded = true }
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onSurface.copy(alpha = ContentAlpha.disabled)
            ),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Canvas(
            modifier = Modifier
                .size(PRIORITY_INDICATOR_SIZE)
                .weight(1f)
        ) {
            drawCircle(color = priority.color)
        }
        Text(
            modifier = Modifier.weight(8f),
            text = priority.name,
            style = MaterialTheme.typography.labelMedium
        )
        IconButton(
            modifier = Modifier
                .alpha(ContentAlpha.medium)
                .rotate(angle)
                .weight(1.5f),
            onClick = { expanded = true }
        ) {
            Icon(
                imageVector = Icons.Filled.ArrowDropDown,
                contentDescription = stringResource(R.string.drop_down_arrow)
            )
        }
        DropdownMenu(
            modifier = Modifier
                .fillMaxWidth(),
            expanded = expanded,
            onDismissRequest = { expanded = false }
        ) {
            DropdownMenuItem(
                onClick = {
                    expanded = false
                    onPrioritySelected(Priority.LOW)
                },
                text = {
                    PriorityItem(priority = Priority.LOW)
                },
            )
            DropdownMenuItem(
                onClick = {
                    expanded = false
                    onPrioritySelected(Priority.MEDIUM)
                },
                text = {
                    PriorityItem(priority = Priority.MEDIUM)
                },
            )
            DropdownMenuItem(
                onClick = {
                    expanded = false
                    onPrioritySelected(Priority.HIGH)
                },
                text = {
                    PriorityItem(priority = Priority.HIGH)
                },
            )
        }
    }
}

@Preview
@Composable
fun PreviewPriorityDropDown() {
    PriorityDropDown(
        priority = Priority.LOW
    ) {}
}
