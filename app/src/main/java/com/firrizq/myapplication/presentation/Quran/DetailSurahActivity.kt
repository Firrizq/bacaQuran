package com.firrizq.myapplication.presentation.Quran

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.firrizq.myapplication.R
import com.firrizq.myapplication.databinding.ActivityDetailSurahBinding
import com.firrizq.myapplication.network.SurahItem

class DetailSurahActivity : AppCompatActivity() {
    private var _binding: ActivityDetailSurahBinding? = null
    private val binding get() = _binding as ActivityDetailSurahBinding

    private var _surah: SurahItem? = null
    private val surah get() = _surah as SurahItem

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityDetailSurahBinding.inflate(layoutInflater)
        setContentView(binding.root)

        _surah = intent.getParcelableExtra(EXTRA_DATA, SurahItem::class.java)

        val quranViewModel = ViewModelProvider(this)[QuranViewModel::class.java]

    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}