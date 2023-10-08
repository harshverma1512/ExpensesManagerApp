package com.example.expensesmanagerapp.viewmodel

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.expensesmanagerapp.model.dtos.Expenses
import com.example.expensesmanagerapp.model.repo.RoomRepository
import com.example.expensesmanagerapp.model.roomDB.ApplicationDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MyViewModel(applicationContext: Application) : ViewModel() {

    val readAllData: LiveData<List<Expenses>>
    private val repository: RoomRepository
    val amountLiveData: LiveData<String>
    val incomeLiveData: LiveData<String>
    val expensesLiveData: LiveData<String>

    init {
        val useDao =
            ApplicationDatabase.getDatabase(applicationContext.applicationContext).expensesDAO()
        repository = RoomRepository(useDao)
        readAllData = repository.repoLiveData
        amountLiveData = repository.amountLiveData
        incomeLiveData = repository.income
        expensesLiveData = repository.expenses
    }

    fun addExpenses(expenses: Expenses) {
        viewModelScope.launch(Dispatchers.IO) { repository.addExpenses(expenses) }
    }

    fun deleteExpenses(expenses: Expenses) {
        viewModelScope.launch(Dispatchers.IO) { repository.deleteExpenses(expenses) }
    }

    fun updateData(
        targetID: Int,
        updateAmount: String,
        updateDate: String,
        updateExpenses: String,
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateData(
                targetID,
                updateAmount,
                updateDate,
                updateExpenses
            )
        }
    }
}