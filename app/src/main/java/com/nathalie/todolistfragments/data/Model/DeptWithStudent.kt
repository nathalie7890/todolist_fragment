package com.nathalie.todolistfragments.data.Model

import androidx.room.Embedded
import androidx.room.Relation

data class DeptWithStudent(
    @Embedded val dept: Dept,
    @Relation(
        parentColumn = "deptId",
        entityColumn = "deptId"
    )
    val students: List<Student>


)
