package com.example.AppEntwicklungTIF18A

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.AppEntwicklungTIF18A.databinding.FragmentSettingsBinding
import com.example.AppEntwicklungTIF18A.databinding.FragmentStatsBinding

class StatsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentStatsBinding.inflate(layoutInflater)
        return binding.root
    }
}