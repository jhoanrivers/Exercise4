package com.example.exercise4.ui.pages.salary

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.exercise4.R
import com.example.exercise4.databinding.ActivityEmployeeBinding
import com.example.exercise4.databinding.ActivitySalaryBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class SalaryActivity : AppCompatActivity() {

    lateinit var binding: ActivitySalaryBinding

    lateinit var adapter: SalaryAdapter

    private val salaryViewModel by viewModels<SalaryViewModel>()



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySalaryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        bindViewEvents()
        bindViewModel()

    }


    private fun initView() {
        adapter = SalaryAdapter()
        salaryViewModel.getEmployeeSalary(intent.getStringExtra("employeeId").orEmpty())
    }

    private fun bindViewEvents() {

        binding.btnAddSalary.setOnClickListener {
            salaryViewModel.addSalary(binding.etAmount.text.toString())
        }

    }

    private fun bindViewModel() {

        salaryViewModel.listSalary.observe(this){
            adapter.updateSalary(it)
            binding.rvSalary.apply {
                adapter = this@SalaryActivity.adapter
                layoutManager = LinearLayoutManager(this@SalaryActivity, LinearLayoutManager.VERTICAL, true)
                itemAnimator = null
            }
        }

        salaryViewModel.currentEmployee.observe(this){
            binding.tvEmployeeName.text = it.name
            binding.tvId.text = it.id
        }

        salaryViewModel.successAddSalary.observe(this){
            salaryViewModel.getSalary()
        }



    }
}