package com.firrizq.myapplication.core.domain.repository

import androidx.lifecycle.LiveData
import com.firrizq.myapplication.core.data.Resource
import com.firrizq.myapplication.core.data.network.NetworkBoundResource
import com.firrizq.myapplication.core.domain.model.City
import com.firrizq.myapplication.core.domain.model.Jadwal
import kotlinx.coroutines.flow.Flow
import java.time.Month
import java.time.Year

interface IAdzanRepository {

    fun getLocation(): LiveData<List<String>>
    fun searchCity(city: String): Flow<Resource<List<City>>>
    fun getDailyAdzanTime(
        id: String,
        year: String,
        month: String,
        date: String
    ): Flow<Resource<Jadwal>>
}