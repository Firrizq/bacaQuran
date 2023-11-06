package com.firrizq.myapplication.data

import android.provider.ContactsContract.Data
import com.firrizq.myapplication.domain.model.QuranEdition
import com.firrizq.myapplication.domain.model.Surah
import com.firrizq.myapplication.domain.repository.IQuranRepository
import com.firrizq.myapplication.network.quran.QuranEditionItem
import com.firrizq.myapplication.network.quran.SurahItem
import com.firrizq.myapplication.utils.DataMapper
import kotlinx.coroutines.flow.*

class QuranRepository (private val remoteDataSource: QuranRemoteDataSource) : IQuranRepository {
    override fun getListSurah(): Flow<Resource<List<Surah>>> {
        return object : NetworkBoundResource<List<Surah>, List<SurahItem>>() {
            override fun fetchFromNetwork(data: List<SurahItem>): Flow<List<Surah>> {
                return DataMapper.mapResponseToDomain(data)
            }

            override suspend fun createCall(): Flow<NetworkResponse<List<SurahItem>>> {
                return remoteDataSource.getListSurah()
            }
        }.asFlow()
    }

    override fun getDetailSurahWithQuranEditions(number: Int): Flow<Resource<List<QuranEdition>>> {
        return object : NetworkBoundResource<List<QuranEdition>, List<QuranEditionItem>>() {
            override fun fetchFromNetwork(data: List<QuranEditionItem>): Flow<List<QuranEdition>> {
                return DataMapper.mapResponseToDomain(data)
            }

            override suspend fun createCall(): Flow<NetworkResponse<List<QuranEditionItem>>> {
                return remoteDataSource.getDetailSurahWithQuranEditions(number)
            }
        }.asFlow()
    }
}