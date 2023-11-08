package com.firrizq.myapplication.core.domain.repository

import com.firrizq.myapplication.core.data.Resource
import com.firrizq.myapplication.core.domain.model.QuranEdition
import com.firrizq.myapplication.core.domain.model.Surah
import kotlinx.coroutines.flow.Flow

interface IQuranRepository {

    fun getListSurah(): Flow<Resource<List<Surah>>>

    fun getDetailSurahWithQuranEditions(number: Int): Flow<Resource<List<QuranEdition>>>
}