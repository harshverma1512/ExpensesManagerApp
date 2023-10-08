package com.example.expensesmanagerapp.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.expensesmanagerapp.model.repo.RoomRepository

    class ViewModelFactory(private val application: Application) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MyViewModel::class.java)) {
            return MyViewModel(application) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
