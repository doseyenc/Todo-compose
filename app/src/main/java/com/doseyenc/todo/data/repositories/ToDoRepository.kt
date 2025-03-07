package com.doseyenc.todo.data.repositories

import com.doseyenc.todo.data.ToDoDao
import com.doseyenc.todo.data.models.ToDoTask
import dagger.hilt.android.scopes.ViewModelScoped
import javax.inject.Inject

@ViewModelScoped
class ToDoRepository @Inject constructor(private val toDoDao: ToDoDao) {

    val getAllTasks = toDoDao.getAllTasks()

    val sortByLowPriority = toDoDao.sortByLowPriority()

    val sortByHighPriority = toDoDao.sortByHighPriority()

    fun searchDatabase(searchQuery: String) = toDoDao.searchDatabase(searchQuery)

    fun getSelectedTask(taskId: Int) = toDoDao.getSelectedTask(taskId)

    suspend fun addTask(toDoTask: ToDoTask) {
        toDoDao.addTask(toDoTask)
    }

    suspend fun updateTask(toDoTask: ToDoTask) {
        toDoDao.updateTask(toDoTask)
    }

    suspend fun deleteTask(toDoTask: ToDoTask) {
        toDoDao.deleteTask(toDoTask)
    }

    suspend fun deleteAllTasks() {
        toDoDao.deleteAllTasks()
    }

}