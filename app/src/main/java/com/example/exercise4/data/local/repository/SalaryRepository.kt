package com.example.exercise4.data.local.repository

import com.example.exercise4.data.local.model.SalaryEntity

interface SalaryRepository {

    fun getSalariesByEmployeeId(employeeId: String) : List<SalaryEntity>

    fun addSalary(salaryEntity: SalaryEntity)
}