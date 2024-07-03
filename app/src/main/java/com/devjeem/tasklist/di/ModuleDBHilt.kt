package com.devjeem.tasklist.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.devjeem.tasklist.database.MainDataBase
import com.devjeem.tasklist.database.dao.TaskingDao
import com.devjeem.tasklist.ui.util.SharedPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ModuleDBHilt {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext context: Context,
    ) = Room.databaseBuilder(context, MainDataBase::class.java, "TASK.db")
        .allowMainThreadQueries().setJournalMode(RoomDatabase.JournalMode.TRUNCATE).build()


    @Singleton
    @Provides
    fun provideDao(db: MainDataBase): TaskingDao = db.taskingDao()

    @Singleton
    @Provides
    fun provideSharedPreference(@ApplicationContext context: Context): SharedPreferences =
        SharedPreferences(context)

}