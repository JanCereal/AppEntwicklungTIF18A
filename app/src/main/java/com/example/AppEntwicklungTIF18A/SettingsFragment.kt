package com.example.AppEntwicklungTIF18A

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.AppEntwicklungTIF18A.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ):View{
        val binding = FragmentSettingsBinding.inflate(layoutInflater)
        return binding.root
    }
}