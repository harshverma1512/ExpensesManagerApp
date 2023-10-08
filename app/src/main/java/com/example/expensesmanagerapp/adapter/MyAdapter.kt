package com.example.expensesmanagerapp.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.expensesmanagerapp.R
import com.example.expensesmanagerapp.databinding.ItemViewBinding
import com.example.expensesmanagerapp.model.dtos.Expenses

class MyAdapter(private val list: List<Expenses>, private val handler: CallBack) :
    RecyclerView.Adapter<MyAdapter.ExpensesViewHolder>() {

    inner class ExpensesViewHolder(binding: ItemViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        val date = binding.date
        val payFor = binding.title
        val amount = binding.amount
        val image = binding.image
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExpensesViewHolder {
        return ExpensesViewHolder(
            ItemViewBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    @SuppressLint("ResourceAsColor", "SetTextI18n")
    override fun onBindViewHolder(holder: ExpensesViewHolder, position: Int) {
        holder.amount.text = "₹ ${list[position].amount}"
        holder.date.text = list[position].date
        holder.payFor.text = list[position].expenses

        holder.itemView.setOnClickListener {
            it.findNavController()
                .navigate(R.id.updateExpensesFragment)
            handler.getTransactionData(list)
        }

        when (holder.payFor.text) {
            "Credit" -> {
                holder.amount.setTextColor(Color.parseColor("#25A969"))
                holder.image.setImageResource(R.drawable.credit)
            }

            "Bills" -> {
                holder.image.setImageResource(R.drawable.bills)
                holder.amount.setTextColor(Color.RED)
            }

            "Travel" -> {
                holder.image.setImageResource(R.drawable.travel)
                holder.amount.setTextColor(Color.RED)
            }

            "Money Transfer" -> {
                holder.image.setImageResource(R.drawable.transfer)
                holder.amount.setTextColor(Color.RED)
            }

            "Service" -> {
                holder.image.setImageResource(R.drawable.gas)
                holder.amount.setTextColor(Color.RED)
            }


            "Shopping" -> {
                holder.image.setImageResource(R.drawable.shopping)
                holder.amount.setTextColor(Color.RED)
            }

            "Games and Fun" -> {
                holder.image.setImageResource(R.drawable.games)
                holder.amount.setTextColor(Color.RED)
            }

            "Recharge" -> {
                holder.image.setImageResource(R.drawable.recharge)
                holder.amount.setTextColor(Color.RED)
            }
        }
    }

    override fun getItemCount(): Int = list.size

    interface CallBack {
        fun getTransactionData(data: List<Expenses>)
    }

}