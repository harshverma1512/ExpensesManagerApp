package com.example.expensesmanagerapp.fragment

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.expensesmanagerapp.R
import com.example.expensesmanagerapp.adapter.MyAdapter
import com.example.expensesmanagerapp.adapter.MyAdapter.CallBack
import com.example.expensesmanagerapp.databinding.HomeFragmentBinding
import com.example.expensesmanagerapp.model.dtos.Expenses
import com.example.expensesmanagerapp.viewmodel.MyViewModel
import com.example.expensesmanagerapp.viewmodel.ViewModelFactory
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class HomeFragment : Fragment() {

    lateinit var binding: HomeFragmentBinding
    private lateinit var adapter: MyAdapter
    private lateinit var viewModel: MyViewModel


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        return HomeFragmentBinding.inflate(layoutInflater, container, false).run {
            binding = this
            root
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.create.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_expensesFragment)
        }

        binding.seeAll.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_transactionFragment)
        }

        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(requireActivity().application)
        )[MyViewModel::class.java]


        binding.Greeting.text = getGreeting()

        binding.recycleView.setHasFixedSize(true)

        viewModel.amountLiveData.observe(viewLifecycleOwner) {
            if (it != null) binding.totalAmount.text = "₹ $it"
            else binding.totalAmount.text = "0"
        }

        viewModel.incomeLiveData.observe(viewLifecycleOwner) {
            if (it != null) binding.income.text = "₹ $it"
            else binding.income.text = "0"
        }

        viewModel.expensesLiveData.observe(viewLifecycleOwner) {
            if (it != null) binding.expenses.text = "₹ $it"
            else binding.expenses.text = "0"
        }

        binding.notification.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_notificationFragment)
        }

        viewModel.readAllData.observe(viewLifecycleOwner) {
            adapter = MyAdapter(it.reversed(), object : CallBack {
                override fun getTransactionData(data: List<Expenses>) {}
            })
            binding.recycleView.adapter = adapter
        }
    }

    private fun getGreeting(): String {
        val currentTime = Calendar.getInstance().time
        return when (SimpleDateFormat("HH", Locale.getDefault()).format(currentTime).toInt()) {
            in 0..11 -> "Good morning,"
            in 12..16 -> "Good afternoon,"
            else -> "Good evening,"
        }
    }
}