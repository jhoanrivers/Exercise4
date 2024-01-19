package com.example.exercise4.ui.pages.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exercise4.R
import com.example.exercise4.data.local.model.EmployeeEntity
import com.example.exercise4.databinding.ActivityEmployeeBinding
import com.example.exercise4.ui.pages.salary.SalaryActivity
import com.example.exercise4.ui.pages.weather.WeatherActivity
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
        employeeAdapter = EmoloyeeAdapter(object: EmoloyeeAdapter.EventListener{
            override fun onClickItem(employeeEntity: EmployeeEntity) {
                startActivity(Intent(this@EmployeeActivity, SalaryActivity::class.java).putExtra("employeeId", employeeEntity.id))
            }
        })
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

        binding.btnWeather.setOnClickListener {
            startActivity(Intent(this@EmployeeActivity, WeatherActivity::class.java))
        }

    }
}