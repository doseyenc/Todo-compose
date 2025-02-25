package com.doseyenc.todo.data.models

import androidx.compose.ui.graphics.Color
import com.doseyenc.todo.ui.theme.HighPriorityColor
import com.doseyenc.todo.ui.theme.LowPriorityColor
import com.doseyenc.todo.ui.theme.MediumPriorityColor
import com.doseyenc.todo.ui.theme.NonePriorityColor

enum class Priority(val color: Color) {
    HIGH(HighPriorityColor),
    MEDIUM(MediumPriorityColor),
    LOW(LowPriorityColor),
    NONE(NonePriorityColor)
}