package com.example.expensesmanagerapp.model.repo

import androidx.lifecycle.LiveData
import com.example.expensesmanagerapp.model.dtos.Expenses
import com.example.expensesmanagerapp.model.roomDB.ExpensesDao

class RoomRepository(private val expensesDao: ExpensesDao) {

    val repoLiveData: LiveData<List<Expenses>> = expensesDao.getExpenses()
    val amountLiveData: LiveData<String> = expensesDao.getTotalAmount()
    val income = expensesDao.getIncomeAmount()
    val expenses = expensesDao.getExpensesAmount()

    suspend fun addExpenses(expenses: Expenses) {
        return expensesDao.insertExpenses(expenses)
    }

    suspend fun deleteExpenses(expenses: Expenses) {
        return expensesDao.deleteExpenses(expenses)
    }

    suspend fun updateData(
        targetID: Int,
        updateAmount: String,
        updateDate: String,
        updateExpenses: String,
    ) {
        return expensesDao.updateData(targetID, updateAmount, updateDate, updateExpenses)
    }
}
