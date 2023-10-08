package com.example.expensesmanagerapp.model.roomDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import com.example.expensesmanagerapp.model.dtos.Expenses

@Database(entities = [Expenses::class], version = 1, exportSchema = false)
abstract class ApplicationDatabase : RoomDatabase() {

    abstract fun expensesDAO(): ExpensesDao

    companion object {
        private var INSTANCE: ApplicationDatabase? = null

        @Synchronized
        fun getDatabase(context: Context): ApplicationDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = databaseBuilder(
                    context.applicationContext,
                    ApplicationDatabase::class.java,
                    "sub_Class_DataBase"
                ).build()
                INSTANCE = instance
                instance
            }

        }
    }
}
