package com.firrizq.myapplication.core.di

import com.firrizq.myapplication.core.data.QuranRemoteDataSource
import com.firrizq.myapplication.core.data.QuranRepository
import com.firrizq.myapplication.core.network.ApiConfig

object Injection {

    fun provideQuranRepository(): QuranRepository {
        val quranApiService = ApiConfig.quranApiConfig
        val quranRemoteDataSource = QuranRemoteDataSource(quranApiService)
        return QuranRepository(quranRemoteDataSource)
    }
}