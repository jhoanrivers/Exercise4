package com.example.exercise4.data.local.repository

import com.example.exercise4.data.local.model.SalaryEntity
import io.realm.Realm
import javax.inject.Inject


class SalaryRepositoryImpl @Inject constructor(): SalaryRepository{


    override fun getSalariesByEmployeeId(employeeId: String): List<SalaryEntity> {
        val realm = Realm.getDefaultInstance()
        return realm.use { realm ->
            realm.where(SalaryEntity::class.java).equalTo("employeeId", employeeId).findAll()
                .let {
                    realm.copyFromRealm(it)
                }
        }
    }

    override fun addSalary(salaryEntity: SalaryEntity) {
        val realm = Realm.getDefaultInstance()
         realm.executeTransaction { realm ->
             realm.copyToRealmOrUpdate(salaryEntity)
         }
    }


}