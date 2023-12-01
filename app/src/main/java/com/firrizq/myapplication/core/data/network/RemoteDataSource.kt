package com.firrizq.myapplication.core.data.network

import android.util.Log
import com.firrizq.myapplication.core.data.network.adzan.AdzanApiService
import com.firrizq.myapplication.core.data.network.adzan.CityItem
import com.firrizq.myapplication.core.data.network.adzan.JadwalItem
import com.firrizq.myapplication.core.data.network.quran.QuranApiService
import com.firrizq.myapplication.core.data.network.quran.QuranEditionItem
import com.firrizq.myapplication.core.data.network.quran.SurahItem
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class RemoteDataSource(
    private val quranApiService: QuranApiService,
    private val adzanApiService: AdzanApiService
) {

    suspend fun getListSurah() : Flow<NetworkResponse<List<SurahItem>>> =
        flow {
            try {
                val surahResponse = quranApiService.getListSurah()
                val listSurah = surahResponse.listSurah
                emit(NetworkResponse.Success(listSurah))
            } catch (e: Exception) {
                emit(NetworkResponse.Error(e.toString()))
                Log.e(RemoteDataSource::class.java.simpleName, "error: " + e.localizedMessage)
            }
        }.flowOn(Dispatchers.IO)

    suspend fun getDetailSurahWithQuranEditions(number: Int): Flow<NetworkResponse<List<QuranEditionItem>>> =
        flow {
            try {
                val ayahResponse = quranApiService.getDetailSurahWithQuranEditions(number)
                val quranEditions = ayahResponse.quranEditionItem
                emit(NetworkResponse.Success(quranEditions))
            } catch (e: Exception) {
                emit(NetworkResponse.Error(e.toString()))
                Log.e(RemoteDataSource::class.java.simpleName, "error: " + e.localizedMessage)
            }
        }.flowOn(Dispatchers.IO)

    suspend fun searchCity(city: String): Flow<NetworkResponse<List<CityItem>>> =
        flow<NetworkResponse<List<CityItem>>> {
            try {
                val cityResponse = adzanApiService.searchCity(city)
                val cities = cityResponse.listCity
                emit(NetworkResponse.Success(cities))
            } catch (e: Exception) {
                emit(NetworkResponse.Error(e.toString()))
                Log.e(RemoteDataSource::class.java.simpleName, "error: " + e.localizedMessage)
            }
        }.flowOn(Dispatchers.IO)

    suspend fun getDailyAdzanTime(
        id: String,
        year: String,
        month: String,
        date: String
    ): Flow<NetworkResponse<JadwalItem>> = flow {
            try {
                val dailyResponse = adzanApiService.getDailyAdzanTime(id, year, month, date)
                val jadwalResponse = dailyResponse.data.jadwalItem
                emit(NetworkResponse.Success(jadwalResponse))
            } catch (e: Exception) {
                emit(NetworkResponse.Error(e.toString()))
                Log.e(RemoteDataSource::class.java.simpleName, "error: " + e.localizedMessage, )
            }
    }.flowOn(Dispatchers.IO)
}