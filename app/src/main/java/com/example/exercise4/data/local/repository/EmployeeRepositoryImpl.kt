package com.example.exercise4.data.local.repository

import com.example.exercise4.data.local.model.EmployeeEntity
import io.realm.Realm
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class EmployeeRepositoryImpl @Inject constructor() : EmployeeRepository {


    override suspend fun getEmployees(): List<EmployeeEntity> {
        val realm = Realm.getDefaultInstance()
//        realll.executeTransaction {
//            for(i in 1..2){
//                it.copyToRealmOrUpdate(EmployeeEntity("employee$i","employee$i@gmail.com"))
//            }
//        }

        val result =  realm.use {
            it.where(EmployeeEntity::class.java).findAll()
        }
        println("result ${result.size}")
        return result
    }

    override suspend fun addEmployee(employeeEntity: EmployeeEntity) {
        val realm = Realm.getDefaultInstance()
        realm.executeTransaction {
            it.copyToRealmOrUpdate(employeeEntity)
        }
    }

    override suspend fun getEmployeeByName(name: String): List<EmployeeEntity> {
        val realm = Realm.getDefaultInstance()
        var employees: List<EmployeeEntity>
        realm.use { realm ->
            val result = realm.where(EmployeeEntity::class.java).equalTo("name", name).findAll()
            employees = realm.copyFromRealm(result)
        }
        return employees
    }
}