package com.example.expensesmanagerapp

import android.Manifest
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.util.Log
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationManagerCompat
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.example.expensesmanagerapp.fragment.ExpensesFragment

class AppNotificationService(context: Context, workerParameters: WorkerParameters) :
    Worker(context, workerParameters) {

    private val channelID = "121"
    private lateinit var notificationManager: NotificationManager
    private lateinit var builder: Notification.Builder

    private fun getNotification() {
        val pendingIntent = PendingIntent.getActivity(
            applicationContext,
            0,
            Intent(applicationContext, ExpensesFragment::class.java),
            PendingIntent.FLAG_UPDATE_CURRENT
        )

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val notificationChannel = NotificationChannel(
                channelID,
                "Expneses Manager Notification",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                enableVibration(true)
                enableLights(true)
            }
            notificationManager =
                applicationContext.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(notificationChannel)

            builder = Notification.Builder(applicationContext, channelID)
                .setContentTitle("Any Expenses Pending ....????")
                .setContentText("if Yes then click here and go to the app")
                .setPriority(Notification.PRIORITY_HIGH)
                .setContentIntent(pendingIntent)
        } else {
            builder = Notification.Builder(applicationContext)
                .setContentTitle("Any Expenses Pending ....????")
                .setContentText("if Yes then click here and go to the app")
                .setPriority(Notification.PRIORITY_HIGH)
                .setContentIntent(pendingIntent)
        }
        val notificationCompat = NotificationManagerCompat.from(applicationContext)
        if (ActivityCompat.checkSelfPermission(
                applicationContext,
                Manifest.permission.POST_NOTIFICATIONS
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        notificationCompat.notify(12, builder.build())
    }

    override fun doWork(): Result {
        Log.d("chal gaya","hua hua hua")
        getNotification()
        return Result.success()
    }
}
