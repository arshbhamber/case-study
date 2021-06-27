package com.target.targetcasestudy.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.target.targetcasestudy.data.entities.Deal
import com.target.targetcasestudy.util.AppConstants
import com.target.targetcasestudy.util.Converters

@Database(entities = [Deal::class], version = 1,exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase: RoomDatabase() {

    abstract fun dealDao(): DealDao

    companion object{
        @Volatile private var instance: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase =
            instance ?: synchronized(this) { instance ?: buildDatabase(context).also { instance = it }}

        private fun buildDatabase(appContext: Context) =
            Room.databaseBuilder(appContext, AppDatabase::class.java,AppConstants.DB_NAME)
                .fallbackToDestructiveMigration()
                .build()
    }

}