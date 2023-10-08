package com.example.expensesmanagerapp.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.expensesmanagerapp.adapter.MyAdapter
import com.example.expensesmanagerapp.databinding.TransactionFragmentBinding
import com.example.expensesmanagerapp.model.dtos.Expenses
import com.example.expensesmanagerapp.viewmodel.MyViewModel
import com.example.expensesmanagerapp.viewmodel.ViewModelFactory

class TransactionFragment : Fragment() {
    private lateinit var binding: TransactionFragmentBinding
    private lateinit var adapter: MyAdapter
    private lateinit var viewModel: MyViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return TransactionFragmentBinding.inflate(layoutInflater).run {
            binding = this
            root
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recycleView.setHasFixedSize(true)

        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(requireActivity().application)
        )[MyViewModel::class.java]

        viewModel.readAllData.observe(viewLifecycleOwner)
        {
            adapter = MyAdapter(it.reversed(), object : MyAdapter.CallBack {
                override fun getTransactionData(data: List<Expenses>) {}
            })
            binding.recycleView.adapter = adapter
        }
    }
}