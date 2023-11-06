package com.firrizq.myapplication.domain.repository

import com.firrizq.myapplication.data.Resource
import com.firrizq.myapplication.domain.model.QuranEdition
import com.firrizq.myapplication.domain.model.Surah
import kotlinx.coroutines.flow.Flow

interface IQuranRepository {

    fun getListSurah(): Flow<Resource<List<Surah>>>

    fun getDetailSurahWithQuranEditions(number: Int): Flow<Resource<List<QuranEdition>>>
}