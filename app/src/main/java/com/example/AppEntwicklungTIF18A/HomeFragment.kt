package com.example.AppEntwicklungTIF18A

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.AppEntwicklungTIF18A.databinding.FragmentHomeBinding
import org.json.JSONObject
import java.io.File


class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        /// Standart Liste wird erstellt und als datei gespeichert falls noch keine besteht
        IO_updateClass.writeCategoryJson(context)

        val binding = FragmentHomeBinding.inflate(layoutInflater)
        binding.btnQuickplay.setOnClickListener { view: View ->
            val bundle = bundleOf("selectedCategory" to getRandomKeywordList())
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

    //TODO : Get RANDOM category for gameFragment
    private fun getRandomKeywordList(): MutableList<String>? {
        var list: ArrayList<Pair<String, MutableList<String>>> = IO_updateClass.getSavedFile(context)
        //TODO 0 mit Random ersetzen und checken ob list.second items hat
        return list[0].second
    }
}