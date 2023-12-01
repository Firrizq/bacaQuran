package com.firrizq.myapplication.core.domain.model

import com.firrizq.myapplication.core.data.Resource

data class DailyAdzanResult(
    val listLocation: List<String>,
    val adzanTime: Resource<Jadwal>,
    val currentDate: List<String>
)
