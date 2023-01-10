package com.nathalie.todolistfragments.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.nathalie.todolistfragments.data.Model.Task
import com.nathalie.todolistfragments.repository.TaskRepository
import kotlinx.coroutines.launch

class EditViewModel(private val repo: TaskRepository): ViewModel() {
    val task: MutableLiveData<Task> = MutableLiveData()

    fun getTaskById(id: Long) {
        viewModelScope.launch {
            val res = repo.getTaskById(id.toInt())
            res?.let {
                task.value = it
            }
        }
    }

    fun editTask(id: Long, task: Task) {
        viewModelScope.launch {
            repo.updateTask(id, task)
        }
    }

    class Provider(val repo: TaskRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return EditViewModel(repo) as T
        }
    }
}