package com.example.exercise4.di

import android.app.Application
import android.content.Context
import com.example.exercise4.data.local.repository.EmployeeRepository
import com.example.exercise4.data.local.repository.EmployeeRepositoryImpl
import com.example.exercise4.data.local.repository.SalaryRepository
import com.example.exercise4.data.local.repository.SalaryRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import io.realm.Realm
import io.realm.RealmConfiguration
import io.realm.log.LogLevel
import io.realm.log.RealmLog
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class LocalModule {


    @Provides
    @Singleton
    fun provideLocalModule(@ApplicationContext context: Context) : Realm {
        Realm.init(context)
        RealmLog.setLevel(LogLevel.ALL)
        val realmConfiguration =  RealmConfiguration
            .Builder()
            .allowWritesOnUiThread(true)
            .name("exercise4db.realm")
            .deleteRealmIfMigrationNeeded()
            .build()
        Realm.setDefaultConfiguration(realmConfiguration)
        return Realm.getInstance(realmConfiguration)
    }

    @Provides
    @Singleton
    fun provideEmployeeRepository() : EmployeeRepository {
        return EmployeeRepositoryImpl()
    }

    @Provides
    @Singleton
    fun provideSalaryRepository() : SalaryRepository {
        return SalaryRepositoryImpl()
    }



}