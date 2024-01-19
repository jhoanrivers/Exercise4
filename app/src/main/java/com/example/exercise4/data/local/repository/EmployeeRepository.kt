package com.example.exercise4.data.local.repository

import com.example.exercise4.data.local.model.EmployeeEntity

interface EmployeeRepository {

    suspend fun getEmployees(): List<EmployeeEntity>

    suspend fun addEmployee(employeeEntity: EmployeeEntity)

    suspend fun getEmployeeByName(name: String) : List<EmployeeEntity>

    suspend fun getEmployeeById(id: String) : EmployeeEntity

}