package com.firrizq.myapplication.core.domain.model

import com.firrizq.myapplication.core.network.quran.AyahsItem
import com.squareup.moshi.Json

data class QuranEdition(
    val number: Int? = null,
    val englishName: String? = null,
    val numberOfAyahs: Int? = null,
    val revelationType: String? = null,
    val name: String? = null,
    val ayahs: List<Ayah>,
    val englishNameTranslation: String? = null
)