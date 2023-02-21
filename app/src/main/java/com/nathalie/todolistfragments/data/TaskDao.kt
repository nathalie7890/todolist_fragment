package com.nathalie.todolistfragments.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.nathalie.todolistfragments.data.Model.*

@Dao
interface TaskDao {
    @Query("SELECT * FROM task")
    suspend fun getTasks(): List<Task>

    @Query("SELECT * FROM task where id= :id")
    suspend fun getTaskById(id: Int): Task?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(task: Task)

    @Query("DELETE FROM task WHERE id = :id")
    suspend fun delete(id: Int)

    @Query("SELECT * FROM task WHERE title = :title2")
    suspend fun getTaskByTitle(title2: String): List<Task>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertDept(dept: Dept)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudent(student: Student)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertSubject(subject: Subject)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStudentSubjectCrossRef(studentSubjectCrossedRef: StudentSubjectCrossedRef)

    @Transaction
    @Query("SELECT * FROM Dept")
    suspend fun getDepartmentWithStudents(): List<DeptWithStudent>


    @Transaction
    @Query("SELECT * FROM student")
    suspend fun getStudentsWithSubjects(): List<StudentsWithSubjects>
}