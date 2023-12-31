package com.firrizq.myapplication.presentation.quran

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.firrizq.myapplication.core.data.QuranRepository
import com.firrizq.myapplication.core.data.Resource
import com.firrizq.myapplication.core.domain.model.QuranEdition
import com.firrizq.myapplication.core.domain.model.Surah

class QuranViewModel (private val quranRepository: QuranRepository): ViewModel() {

    fun getListSurah(): LiveData<Resource<List<Surah>>> =
        quranRepository.getListSurah().asLiveData()

    fun getDetailSurahWithQuranEditions(number: Int): LiveData<Resource<List<QuranEdition>>> =
        quranRepository.getDetailSurahWithQuranEditions(number).asLiveData()
}