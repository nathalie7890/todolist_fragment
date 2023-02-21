package com.nathalie.todolistfragments.repository

import com.nathalie.todolistfragments.data.Model.*
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

    suspend fun insertDept(dept: Dept) {
        taskDao.insertDept(dept)
    }

    suspend fun insertStudent(student: Student) {
        taskDao.insertStudent(student)
    }

    suspend fun insertSubject(subject: Subject) {
        taskDao.insertSubject(subject)
    }

    suspend fun insertStudentSubjectCrossRef(studentSubjectCrossedRef: StudentSubjectCrossedRef) {
        taskDao.insertStudentSubjectCrossRef(studentSubjectCrossedRef)
    }


    suspend fun getDepartmentWithStudent(): List<DeptWithStudent> {
        return taskDao.getDepartmentWithStudents()
    }

    suspend fun getStudentsWithSubjects(): List<StudentsWithSubjects> {
        return taskDao.getStudentsWithSubjects()
    }
}

