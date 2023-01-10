package com.nathalie.todolistfragments.repository

import com.nathalie.todolistfragments.data.Model.Task
import com.nathalie.todolistfragments.data.TaskDao

class TaskRepository(private val taskDao: TaskDao) {

    suspend fun getTasks(): List<Task> {
        return taskDao.getTasks()
    }

    suspend fun addTask(task: Task) {
        taskDao.insert(task)
    }

    suspend fun getTaskById(id: Int): Task? {
        return taskDao.getTaskById(id)
    }

    suspend fun updateTask(id: Long, task: Task) {
        taskDao.insert(task.copy(id = id))
    }

    suspend fun deleteTask(id: Int) {
        taskDao.delete(id)
    }

    suspend fun getTaskByTitle(title: String): List<Task> {
        return taskDao.getTaskByTitle(title)
    }

}

