package com.example.expensesmanagerapp.helpers

import android.content.Context
import android.content.SharedPreferences
class AppPreferences{

    private lateinit var sharedPreferences : SharedPreferences
    private lateinit var editor : SharedPreferences.Editor

    fun saveSharedPreference(context: Context){
        sharedPreferences = context.getSharedPreferences("ExpensesManager",Context.MODE_PRIVATE)
        editor = sharedPreferences.edit()
    }

    companion object {
        val instance : AppPreferences = AppPreferences()
        const val User_Name = ""
        const val First_Time_User = false
    }

    fun setUserName(name : String){
       editor.putString(User_Name,name)
        editor.apply()
    }

    fun setFirstTimeUser(isFirstTime : Boolean){
        editor.putBoolean(isFirstTime.toString(),false)
        editor.apply()
    }

    fun getFirstTimeUser() : Boolean{
        return sharedPreferences.getBoolean(First_Time_User.toString(),false)
    }
    fun getUserName() : String? {
    return  sharedPreferences.getString(User_Name,"")
    }
}