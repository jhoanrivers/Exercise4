package com.example.exercise4.ui.pages.salary

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.exercise4.data.local.model.EmployeeEntity
import com.example.exercise4.data.local.model.SalaryEntity
import com.example.exercise4.data.local.repository.EmployeeRepository
import com.example.exercise4.data.local.repository.SalaryRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import io.realm.Realm
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject


@HiltViewModel
class SalaryViewModel @Inject constructor(
    private val employeeRepository: EmployeeRepository,
    private val salaryRepository: SalaryRepository,

) : ViewModel() {


    val listSalary = MutableLiveData<List<SalaryEntity>>()
    val currentEmployee = MutableLiveData<EmployeeEntity>()
    lateinit var haloha: EmployeeEntity
    private var realm = Realm.getDefaultInstance()
    val successAddSalary = MutableLiveData<Boolean>()




    fun getEmployeeSalary(id: String) {

        viewModelScope.launch {
            try {
                val employee = employeeRepository.getEmployeeById(id)
                val salaries = salaryRepository.getSalariesByEmployeeId(employee.id)
                withContext(Dispatchers.Main){
                    listSalary.postValue(salaries)
                    currentEmployee.postValue(employee)
                }
                haloha = employee
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }

    }


    override fun onCleared() {
        super.onCleared()
        realm.close()
    }

    fun addSalary(amount: String) {
        viewModelScope.launch {
            try {
                val salaryEntity = SalaryEntity(haloha.id,amount )
                salaryRepository.addSalary(salaryEntity)
                successAddSalary.postValue(true)
            }catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }

    fun getSalary() {
        getEmployeeSalary(haloha.id)
    }


}