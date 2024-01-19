package com.example.exercise4.ui.pages.salary

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.exercise4.data.local.model.SalaryEntity
import com.example.exercise4.databinding.ItemSalaryBinding

class SalaryAdapter : RecyclerView.Adapter<SalaryAdapter.ViewHolder>(){

    var listSalary = listOf<SalaryEntity>()

    class ViewHolder (val binding: ItemSalaryBinding) : RecyclerView.ViewHolder(binding.root){

        fun bind(item : SalaryEntity) {
            binding.tvSalaryTitle.text = item.amount
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ItemSalaryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return listSalary.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listSalary[position])
    }

    fun updateSalary(it: List<SalaryEntity>) {
        this.listSalary = it
        notifyDataSetChanged()
    }
}