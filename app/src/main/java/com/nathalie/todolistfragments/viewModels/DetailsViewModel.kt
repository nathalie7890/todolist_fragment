package com.nathalie.todolistfragments.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.nathalie.todolistfragments.data.Model.Task
import com.nathalie.todolistfragments.repository.TaskRepository
import com.nathalie.todolistfragments.repository.TaskRepositoryFake
import kotlinx.coroutines.launch

class DetailsViewModel(private val repo: TaskRepository) : ViewModel() {
    val task: MutableLiveData<Task> = MutableLiveData()

    fun getTaskById(id: Long) {
        viewModelScope.launch {
            val res = repo.getTaskById(id.toInt())
            res?.let {
                task.value = it
            }
        }

    }

    fun deleteTask(id: Long) {
        viewModelScope.launch {
            repo.deleteTask(id.toInt())
        }
    }

    class Provider(val repo: TaskRepository) : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return DetailsViewModel(repo) as T
        }
    }
}