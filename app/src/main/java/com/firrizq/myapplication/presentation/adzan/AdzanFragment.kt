package com.firrizq.myapplication.presentation.adzan

import android.location.Geocoder
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.firrizq.myapplication.R
import com.firrizq.myapplication.databinding.FragmentAdzanBinding
import com.firrizq.myapplication.databinding.FragmentQuranBinding
import com.firrizq.myapplication.presentation.SharedViewModel
import com.firrizq.myapplication.presentation.ViewModelFactory
import com.google.android.material.snackbar.Snackbar
import java.util.Locale

class AdzanFragment : Fragment() {
    private var _binding: FragmentAdzanBinding? = null
    private val binding get() = _binding as FragmentAdzanBinding

    private val sharedViewModel: SharedViewModel by activityViewModels { ViewModelFactory(requireContext()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAdzanBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedViewModel.getKnownLastLocation()
        sharedViewModel.lastKnownLocation.observe(viewLifecycleOwner) {
            if (it != null) {
                val geocoder = Geocoder(requireActivity(), Locale.getDefault())
                geocoder.getFromLocation(
                    it[0], // latitude
                    it[1], //longitude
                    1
                ) { listAddress ->
                    val city = listAddress[0].subAdminArea
                    val resultOfCity = city.split(" ")
                    binding.tvLocation.text = resultOfCity[1]
                }
            } else {
                Toast.makeText(context, "Sorry, Something wrong", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}