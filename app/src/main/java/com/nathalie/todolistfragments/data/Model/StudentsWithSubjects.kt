package com.nathalie.todolistfragments.data.Model

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

data class StudentsWithSubjects(
    @Embedded val student: Student,
    @Relation(
        parentColumn = "studentId",
        entityColumn = "subjectId",
        associateBy = Junction(StudentSubjectCrossedRef::class)
    )

    val subject: List<Subject>
)