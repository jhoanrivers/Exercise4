package com.example.exercise4.ui.pages.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.exercise4.data.local.model.EmployeeEntity
import com.example.exercise4.databinding.ItemEmployeeBinding

class EmoloyeeAdapter : RecyclerView.Adapter<EmoloyeeAdapter.ViewHolder>() {


    var listEmployee = listOf<EmployeeEntity>()


    fun updateEmployee(items: List<EmployeeEntity>) {
        listEmployee = items
        println("adapter item size ${listEmployee.size}")
        notifyDataSetChanged()
    }


    class ViewHolder(private val binding: ItemEmployeeBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(item: EmployeeEntity) {
            binding.tvName.text = item.name
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemEmployeeBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listEmployee.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listEmployee[position])
    }
}