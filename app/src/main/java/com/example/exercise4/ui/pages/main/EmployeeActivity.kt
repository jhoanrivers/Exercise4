package com.example.exercise4.ui.pages.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exercise4.R
import com.example.exercise4.databinding.ActivityEmployeeBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import javax.inject.Named


@AndroidEntryPoint
class EmployeeActivity : AppCompatActivity() {


    lateinit var binding: ActivityEmployeeBinding

    lateinit var employeeAdapter: EmoloyeeAdapter

    private val employeeViewModel by viewModels<EmployeeViewModel>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEmployeeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        bindViewModel()
        bindViewEvents()
    }

    fun initView() {
        employeeAdapter = EmoloyeeAdapter()
        employeeViewModel.getAllEmployee()

    }


    fun bindViewModel() {

        employeeViewModel.employeesData.observe(this){
            employeeAdapter.updateEmployee(it)
            binding.rvEmployee.apply {
                adapter = this@EmployeeActivity.employeeAdapter
                layoutManager = LinearLayoutManager(this@EmployeeActivity, LinearLayoutManager.VERTICAL, true)
                itemAnimator = null
            }

        }

        employeeViewModel.successData.observe(this) {
            employeeViewModel.getAllEmployee()
        }


    }

    fun bindViewEvents() {

        binding.btnAdd.setOnClickListener {
            employeeViewModel.addEmployee(binding.etEName.text.toString())
        }

        binding.imgRefresh.setOnClickListener {
            employeeViewModel.getAllEmployee()
        }


    }
}