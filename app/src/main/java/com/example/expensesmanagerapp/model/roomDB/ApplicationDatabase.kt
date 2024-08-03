package com.example.expensesmanagerapp.model.roomDB

import android.content.Context
import androidx.room.Database
import androidx.room.Room.databaseBuilder
import androidx.room.RoomDatabase
import com.example.expensesmanagerapp.model.dtos.Expenses
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Database(entities = [Expenses::class], version = 1, exportSchema = false)
abstract class ApplicationDatabase : RoomDatabase() {

    abstract fun expensesDAO(): ExpensesDao

    @InstallIn(SingletonComponent::class)
    @Module
  object DataBaseModule{
        private var INSTANCE: ApplicationDatabase? = null

        @Synchronized
        @Singleton
        @Provides
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
