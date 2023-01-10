package com.nathalie.todolistfragments.data.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Task(
    @PrimaryKey
    val id: Long? = null,
    val title: String,
    val date: String,
    val details: String,
    val priority: Int
)
