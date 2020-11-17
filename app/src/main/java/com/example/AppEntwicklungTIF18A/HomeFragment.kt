package com.example.AppEntwicklungTIF18A

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.AppEntwicklungTIF18A.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val keywordList = mutableListOf("water", "tree", "dirt", "cat")
        val bundle = bundleOf("KeywordList" to keywordList)




        val binding = FragmentHomeBinding.inflate(layoutInflater)
        binding.btnQuickplay.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_homeFragment_to_gameFragment, bundle)
        }
        binding.btnSettings.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_homeFragment_to_settingsFragment)
        }
        binding.btnCategory.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_homeFragment_to_categoryFragment)
        }
        return binding.root
    }
}