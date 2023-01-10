package com.nathalie.todolistfragments.repository

import com.nathalie.todolistfragments.data.Model.Task

class TaskRepositoryFake {
    private var counter = 0L
    private val taskMap: MutableMap<Long, Task> = mutableMapOf(
        0L to Task(0L, "Bug 10001", "23/12/22", "Fix Bug", 8)
    )
    fun getTasks(): List<Task> {
        return taskMap.values.toList()
    }

    fun addTask(task: Task): Task? {
        taskMap[++counter] = task.copy(id = counter)
        return taskMap[counter]
    }

    fun getTaskById(id: Long): Task? {
        return taskMap[id]
    }

    fun updateTask(id: Long, task: Task): Task? {
        taskMap[id] = task
        return taskMap[id]
    }

    fun deleteTask(id: Long) {
        taskMap.remove(id)
    }

    companion object {
        var taskRepositoryFake: TaskRepositoryFake? = null

        fun getInstance(): TaskRepositoryFake {
            if (taskRepositoryFake == null) {
                taskRepositoryFake = TaskRepositoryFake()
            }

            return taskRepositoryFake!!
        }
    }
}

