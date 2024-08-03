package com.example.expensesmanagerapp.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.content.res.Configuration.UI_MODE_NIGHT_MASK
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.expensesmanagerapp.R
import dagger.hilt.android.AndroidEntryPoint

@SuppressLint("CustomSplashScreen")
@AndroidEntryPoint
class SplashScreen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        checkSystemTheme()

        Handler(Looper.getMainLooper()).postDelayed({
            finish()
            startActivity(Intent(this, MainActivity::class.java))
        }, 1000)
    }
    private fun checkSystemTheme() {
        val currentNightMode = resources.configuration.uiMode and UI_MODE_NIGHT_MASK
        if (currentNightMode == UI_MODE_NIGHT_YES) {
            // Set your app's theme to dark mode here
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                // For Android 10 (API level 29) and above
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
            } else {
                // For versions below Android 10
                setTheme(R.style.Base_Theme_ExpensesManagerApp)
            }
        }
    }
}