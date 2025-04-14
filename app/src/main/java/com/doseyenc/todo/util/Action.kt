package com.doseyenc.todo.util

enum class Action {
    ADD,
    UPDATE,
    DELETE,
    DELETE_ALL,
    UNDO,
    NO_ACTION
}

fun String?.toAction(): Action {
    return when (this) {
        Action.ADD.name -> Action.ADD
        Action.UPDATE.name -> Action.UPDATE
        Action.DELETE.name -> Action.DELETE
        Action.DELETE_ALL.name -> Action.DELETE_ALL
        Action.UNDO.name -> Action.UNDO
        else -> Action.NO_ACTION
    }
}