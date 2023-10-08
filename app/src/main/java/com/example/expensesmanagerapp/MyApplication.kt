package com.example.expensesmanagerapp

import android.annotation.SuppressLint
import android.app.Application
import androidx.work.Constraints
import androidx.work.NetworkType
import androidx.work.PeriodicWorkRequest
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        setupWorker()
    }

    @SuppressLint("InvalidPeriodicWorkRequestInterval")
    private fun setupWorker() {
        val constraint = Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
        val workRequest =
            PeriodicWorkRequest.Builder(AppNotificationService::class.java, 5, TimeUnit.SECONDS)
                .setConstraints(constraint).build()

        WorkManager.getInstance(applicationContext).enqueue(workRequest)
    }
}