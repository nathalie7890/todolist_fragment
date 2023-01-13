package com.nathalie.todolistfragments.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.nathalie.todolistfragments.data.Model.Task
import com.nathalie.todolistfragments.data.Model.User


@Database(entities = [Task::class, User::class], version = 3)
abstract class TodoListDatabase : RoomDatabase() {
    abstract val taskDao: TaskDao

    companion object {
        const val DATABASE_NAME = "todo_list_database"
    }
}