package com.firrizq.myapplication.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface QuranApiService {
    @GET ("surah")
    fun getListSurah() : Call<SurahResponse>

    @GET ("https://api.alquran.cloud/v1/surah/114/editions/quran-uthmani,ar.alafasy,id.indonesian")
    fun getListAyahs(
        @Path("number") number: Int
    ) : Call<AyahResponse>
}