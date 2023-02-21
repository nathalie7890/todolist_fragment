package com.nathalie.todolistfragments.data.Model

import androidx.room.Entity

@Entity(primaryKeys = ["studentId", "subjectId"])
data class StudentSubjectCrossedRef(
    val studentId: Int,
    val subjectId: Int
)
