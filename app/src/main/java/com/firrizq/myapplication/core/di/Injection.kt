package com.firrizq.myapplication.core.di

import android.content.Context
import com.firrizq.myapplication.core.data.AdzanRepository
import com.firrizq.myapplication.core.data.network.RemoteDataSource
import com.firrizq.myapplication.core.data.QuranRepository
import com.firrizq.myapplication.core.data.local.CalendarPreferences
import com.firrizq.myapplication.core.data.local.LocationPreferences
import com.firrizq.myapplication.core.data.network.ApiConfig

object Injection {

    val quranApiService = ApiConfig.quranApiService
    val adzanApiService = ApiConfig.adzanApiService
    val remoteDataSource = RemoteDataSource(quranApiService, adzanApiService)

    fun provideQuranRepository(): QuranRepository {
        return QuranRepository(remoteDataSource)
    }

    fun provideAdzanRepository(context: Context): AdzanRepository {
        val locationPreferences = LocationPreferences(context)
        val calendarPreferences = CalendarPreferences()
        return AdzanRepository(remoteDataSource, locationPreferences, calendarPreferences)
    }

}