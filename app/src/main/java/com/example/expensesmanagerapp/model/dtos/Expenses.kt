package com.example.expensesmanagerapp.model.dtos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity()
class Expenses(
    @PrimaryKey(autoGenerate = true)
    var id: Int,
    @ColumnInfo(name = "expenses")
    val expenses: String,
    @ColumnInfo(name = "amount")
    val amount: String,
    @ColumnInfo(name = "date")
    val date: String,
)