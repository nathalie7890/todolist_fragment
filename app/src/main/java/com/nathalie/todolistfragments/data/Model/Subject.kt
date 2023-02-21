package com.nathalie.todolistfragments.data.Model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Subject(
    @PrimaryKey(autoGenerate = true)
    val subjectId: Int? = null,
    val name: String
)
