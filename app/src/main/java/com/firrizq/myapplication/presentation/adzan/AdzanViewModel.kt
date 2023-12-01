package com.firrizq.myapplication.presentation.adzan

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.firrizq.myapplication.core.data.AdzanRepository
import com.firrizq.myapplication.core.data.Resource
import com.firrizq.myapplication.core.domain.model.DailyAdzanResult

class AdzanViewModel(private val adzanRepository: AdzanRepository): ViewModel() {

    fun getDailyAdzanTime(): LiveData<Resource<DailyAdzanResult>> = adzanRepository.getDailyAdzanTimeResult()
}