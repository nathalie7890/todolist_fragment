package com.nathalie.todolistfragments.viewModels

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.nathalie.todolistfragments.data.Model.*
import com.nathalie.todolistfragments.repository.TaskRepository
import com.nathalie.todolistfragments.repository.TaskRepositoryFake
import kotlinx.coroutines.launch

class HomeViewModel(val repo: TaskRepository) : ViewModel() {
    val tasks: MutableLiveData<List<Task>> = MutableLiveData()
    val counter: MutableLiveData<String> = MutableLiveData()

    val departments = listOf(
        Dept(name = "CSE"),
        Dept(name = "EEE")
    )
    val students = listOf(
        Student(name = "Aang", deptId = 1),
        Student(name = "Katara", deptId = 1),
        Student(name = "Sokka", deptId = 1),
    )

    private val subjects = listOf(
        Subject(name = "Algorithm"),
        Subject(name = "Vector Analysis"),
        Subject(name = "Cryptography"),
        Subject(name = "Number Theory")
    )

    private val studentSubject = listOf(
        StudentSubjectCrossedRef(1, 1),
        StudentSubjectCrossedRef(1, 2),
        StudentSubjectCrossedRef(1, 4)
    )

    init {
        getTasks()
        viewModelScope.launch {
            departments.forEach {
                repo.insertDept(it)
            }

            students.forEach {
                repo.insertStudent(it)
            }

            subjects.forEach {
                repo.insertSubject(it)
            }

            studentSubject.forEach {
                repo.insertStudentSubjectCrossRef(it)
            }

            val res = repo.getDepartmentWithStudent()
            Log.d("Debugging", res.toString())
            res.forEach {
                Log.d("debugging", "${it.dept}")
                Log.d("debugging", "${it.students}")
            }

            val res2 = repo.getStudentsWithSubjects()
            Log.d("Debugging", res2.toString())
            res2.forEach {
                Log.d("debugging", "${it.student}")
                Log.d("debugging", "${it.subject}")
            }
        }
    }

    fun getTasks() {
        viewModelScope.launch {
            val res = repo.getTasks()
            tasks.value = res
        }

    }

    class Provider(val repo: TaskRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return HomeViewModel(repo) as T
        }
    }

}