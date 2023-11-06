package com.firrizq.myapplication.presentation.Quran

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.firrizq.myapplication.data.QuranRepository
import com.firrizq.myapplication.data.Resource
import com.firrizq.myapplication.domain.model.QuranEdition
import com.firrizq.myapplication.domain.model.Surah
import com.firrizq.myapplication.network.ApiConfig
import com.firrizq.myapplication.network.quran.AyahResponse
import com.firrizq.myapplication.network.quran.SurahResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QuranViewModel (private val quranRepository: QuranRepository): ViewModel() {

    fun getListSurah(): LiveData<Resource<List<Surah>>> =
        quranRepository.getListSurah().asLiveData()

    fun getDetailSurahWithQuranEditions(number: Int): LiveData<Resource<List<QuranEdition>>> =
        quranRepository.getDetailSurahWithQuranEditions(number).asLiveData()
}