package com.nathalie.todolistfragments.data

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.nathalie.todolistfragments.data.Model.*


@Database(
    entities = [Task::class, User::class, Student::class, Dept::class, Subject::class, StudentSubjectCrossedRef::class],
    version = 4
)
abstract class TodoListDatabase : RoomDatabase() {
    abstract val taskDao: TaskDao

    companion object {
        const val DATABASE_NAME = "todo_list_database"

        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL("ALTER TABLE Student ADD age INT NOT NULL DEFAULT 0")
            }
        }
    }
}