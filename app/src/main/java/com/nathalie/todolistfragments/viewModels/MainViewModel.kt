package com.nathalie.todolistfragments.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nathalie.todolistfragments.data.Model.Task

class MainViewModel: ViewModel() {
    private var counter = 0L
    private val tasksMap: MutableMap<Long, Task> = mutableMapOf(
        0L to Task(id = 0, "Hello", "1/12/12", "World", 1)
    )
    val tasks: MutableLiveData<List<Task>> = MutableLiveData()

    init {
        tasks.value = tasksMap.values.toList()
    }
}