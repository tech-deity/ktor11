package com.techdeity.model.dao

import com.techdeity.model.Student

interface StudentDao {


    //to perform background task
    suspend fun insert(
        name:String,
        age:Int
    ):Student?

    suspend fun getAllStudents():List<Student>?

    suspend fun getStudentById(userId:Int):Student?

    suspend fun deleteById(userId:Int):Int

    suspend fun update(
        userId: Int,
        name: String,
        age: Int
    ):Int


}