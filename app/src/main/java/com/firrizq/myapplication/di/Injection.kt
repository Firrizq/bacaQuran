package com.firrizq.myapplication.di

import com.firrizq.myapplication.data.QuranRemoteDataSource
import com.firrizq.myapplication.data.QuranRepository
import com.firrizq.myapplication.network.ApiConfig

object Injection {

    fun provideQuranRepository(): QuranRepository {
        val quranApiService = ApiConfig.quranApiConfig
        val quranRemoteDataSource = QuranRemoteDataSource(quranApiService)
        return QuranRepository(quranRemoteDataSource)
    }
}