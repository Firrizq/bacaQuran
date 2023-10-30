package com.firrizq.myapplication.presentation.Quran

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.firrizq.myapplication.R
import com.firrizq.myapplication.adapter.SurahAdapter
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

        initView()

        val mAdapter = SurahAdapter()

        val quranViewModel = ViewModelProvider(this)[QuranViewModel::class.java]
        surah.number?.let { quranViewModel.getListAyahbySurah(it) }
        quranViewModel.listAyah.observe(this) {
            mAdapter.setData(it.quranEdition?.get(0)?.ayahs, it.quranEdition)
            binding.rvSurah.apply {
                adapter = mAdapter
                layoutManager = LinearLayoutManager(this@DetailSurahActivity)
            }
        }
    }

    private fun initView() {
        binding.apply {
            tvDetailSurah.text = surah.englishName
            tvDetailNameTranslation.text = surah.englishNameTranslation
            val revelationSurah = surah.revelationType
            val numberAyahs = surah.numberOfAyahs
            val resultAyah = "$revelationSurah - $numberAyahs Ayahs"
            tvDetailAyah.text = resultAyah
            tvDetailName.text = surah.name
        }
    }

    companion object {
        const val EXTRA_DATA = "extra_data"
    }
}