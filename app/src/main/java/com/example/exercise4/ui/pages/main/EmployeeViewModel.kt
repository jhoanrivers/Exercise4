package com.example.exercise4.ui.pages.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exercise4.data.local.model.EmployeeEntity
import com.example.exercise4.data.local.repository.EmployeeRepository
import com.example.exercise4.data.local.repository.SalaryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.realm.Realm
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class EmployeeViewModel @Inject constructor(
    val employeeRepository: EmployeeRepository,
    val salaryRepository: SalaryRepository,
    val realm: Realm
) : ViewModel() {


    val employeesData = MutableLiveData<List<EmployeeEntity>>()
    val successData = MutableLiveData<Boolean>()
    val errorData = MutableLiveData<String>()


    fun getAllEmployee() {

        try {
            viewModelScope.launch {
                try {
                    println("start calling")
                    val response = employeeRepository.getEmployees()
                    println("sizenya ${response.size}")
                    withContext(Dispatchers.Main) {
                        employeesData.postValue(response)
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        errorData.postValue(e.message)
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
            errorData.postValue(e.message)
        }

    }

    fun addEmployee(toString: String) {
        try {
            viewModelScope.launch {
                val employee = EmployeeEntity(
                    name = toString,
                    email = "samplemail@gmail.com"
                )
                employeeRepository.addEmployee(employee)
                withContext(Dispatchers.Main) {
                    successData.postValue(true)
                }

            }
        } catch (e: Exception) {
            e.printStackTrace()
            errorData.postValue(e.message)
        }
    }

    override fun onCleared() {
        super.onCleared()
        realm.close()
    }


}