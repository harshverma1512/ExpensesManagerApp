package com.example.expensesmanagerapp.fragment

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.example.expensesmanagerapp.adapter.MyAdapter
import com.example.expensesmanagerapp.databinding.UpdateFragmentBinding
import com.example.expensesmanagerapp.model.dtos.Expenses
import com.example.expensesmanagerapp.viewmodel.MyViewModel
import com.example.expensesmanagerapp.viewmodel.ViewModelFactory
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class UpdateExpensesFragment : Fragment(), MyAdapter.CallBack {

    private lateinit var binding: UpdateFragmentBinding
    lateinit var amount: String
    lateinit var date: String
    private var dataId: Int = 0
    private lateinit var viewModel: MyViewModel
    lateinit var payFor: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return UpdateFragmentBinding.inflate(inflater, container, false).run {
            binding = this
            root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.payfor.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long,
            ) {
                payFor = parent?.adapter?.getItem(position).toString()
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {}
        }
        binding.date.setOnClickListener {
            showDatePickerDialog()
        }

        binding.back.setOnClickListener {
            it.findNavController().popBackStack()
        }
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(requireActivity().application)
        )[MyViewModel::class.java]

        binding.done.setOnClickListener {
            insertData()
        }
    }

    @SuppressLint("WeekBasedYear")
    private fun showDatePickerDialog() {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(
            requireContext(),
            { _, selectedYear, selectedMonth, selectedDay ->
                val dateFormat = SimpleDateFormat("dd/MM/YYYY", Locale.US)
                val selectedDate = dateFormat.format(Calendar.getInstance().apply {
                    set(selectedYear, selectedMonth, selectedDay)
                }.time)
                binding.date.text = selectedDate
            },
            year,
            month,
            day
        )

        datePickerDialog.show()
    }

    private fun insertData() {
        val amount = binding.amount.text
        val date = binding.date.text
        if (amount?.isEmpty() == true) {
            binding.amount.error = "Enter the Amount"
        } else if (date?.isEmpty() == true) {
            binding.date.error = "Enter the Date"
        } else {
            viewModel.updateData(dataId, amount.toString(), date.toString(), payFor)
        }
        Toast.makeText(requireContext(), "Update SuccessFully", Toast.LENGTH_LONG).show()
        findNavController().popBackStack()
    }

    override fun getTransactionData(data: List<Expenses>) {
        binding.amount.setText(data[0].amount)
        binding.date.text = data[0].date
    }
}