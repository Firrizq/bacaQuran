package com.firrizq.myapplication.utils

import com.firrizq.myapplication.core.domain.model.Ayah
import com.firrizq.myapplication.core.domain.model.City
import com.firrizq.myapplication.core.domain.model.QuranEdition
import com.firrizq.myapplication.core.domain.model.Surah
import com.firrizq.myapplication.core.data.network.adzan.CityItem
import com.firrizq.myapplication.core.data.network.adzan.JadwalItem
import com.firrizq.myapplication.core.data.network.quran.AyahsItem
import com.firrizq.myapplication.core.data.network.quran.QuranEditionItem
import com.firrizq.myapplication.core.data.network.quran.SurahItem
import com.firrizq.myapplication.core.domain.model.Jadwal
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

object DataMapper {

    @JvmName("mapSurahItemToDomain")
    fun mapResponseToDomain(input: List<SurahItem>): Flow<List<Surah>> {
        val listSurah = ArrayList<Surah>()
        input.map {
            val surah = Surah(
                number = it.number,
                englishName = it.englishName,
                numberOfAyahs = it.numberOfAyahs,
                revelationType = it.revelationType,
                name = it.name,
                englishNameTranslation = it.englishNameTranslation
            )
            listSurah.add(surah)
        }
        return flowOf(listSurah)
    }

    @JvmName("mapQuranEditionItemDomain")
    fun mapResponseToDomain(input: List<QuranEditionItem>) : Flow<List<QuranEdition>> {
        val quranEditions = ArrayList<QuranEdition>()
        input.map {
            val edition = QuranEdition(
                number = it.number,
                englishName = it.englishName,
                numberOfAyahs = it.numberOfAyahs,
                revelationType = it.revelationType,
                name = it.name,
                englishNameTranslation = it.englishNameTranslation,
                ayahs = mapAyahsItemToDomain(it.ayahs)
            )
            quranEditions.add(edition)
        }
        return flowOf(quranEditions)
    }

    private fun mapAyahsItemToDomain(input: List<AyahsItem>) : List<Ayah> {
        val listAyah = ArrayList<Ayah>()
        input.map {
            val ayah = Ayah(
                number = it.number,
                text = it.text,
                numberInSurah = it.numberInSurah,
                audio = it.audio
            )
            listAyah.add(ayah)
        }
        return listAyah
    }

    @JvmName("mapCityItemToDomain")
    fun mapResponseToDomain(input: List<CityItem>): Flow<List<City>> {
        val cities = ArrayList<City>()
        input.map {
            val city = City(
                id = it.id,
                location = it.lokasi
            )
            cities.add(city)
        }
        return flowOf(cities)
    }

    @JvmName("mapJadwalItemToDomain")
    fun mapResponseToDomain(input: JadwalItem): Flow<Jadwal>{
        val jadwal = Jadwal(
            date = input.date,
            imsak = input.imsak,
            isya = input.isya,
            subuh = input.subuh,
            dzuhur = input.dzuhur,
            ashar = input.ashar,
            dhuha = input.dhuha,
            terbit = input.terbit,
            tanggal = input.tanggal,
            maghrib = input.maghrib
        )
        return flowOf(jadwal)
    }

}