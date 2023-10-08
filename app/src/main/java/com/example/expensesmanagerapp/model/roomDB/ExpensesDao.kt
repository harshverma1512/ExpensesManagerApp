package com.example.expensesmanagerapp.model.roomDB

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.expensesmanagerapp.model.dtos.Expenses

@Dao
interface ExpensesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExpenses(expenses: Expenses)

    @Delete
    suspend fun deleteExpenses(expenses: Expenses)

    @Query("Select * from Expenses")
    fun getExpenses(): LiveData<List<Expenses>>

    @Query("SELECT SUM(amount) FROM Expenses")
    fun getTotalAmount(): LiveData<String>

    @Query("SELECT SUM(amount) FROM Expenses where expenses != 'Credit' ")
    fun getExpensesAmount(): LiveData<String>

    @Query("SELECT SUM(amount) FROM Expenses where expenses = 'Credit'")
    fun getIncomeAmount(): LiveData<String>

    @Query("update Expenses set amount =:updateAmount, date =:updateDate , expenses =:updateExpenses where id =:targetID")
    suspend fun updateData(
        targetID: Int,
        updateAmount: String,
        updateDate: String,
        updateExpenses: String,
    )
}