package com.example.AppEntwicklungTIF18A

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.AppEntwicklungTIF18A.databinding.FragmentHomeBinding
import kotlin.random.Random

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        IO_updateClass.writeCategoryJson(context)
        val binding = FragmentHomeBinding.inflate(layoutInflater)

        binding.btnQuickplay.setOnClickListener { view: View ->
            val bundle = bundleOf("selectedCategory" to getRandomKeywordList())
            view.findNavController().navigate(R.id.action_homeFragment_to_gameFragment, bundle)
        }
        return binding.root
    }

    private fun getRandomKeywordList(): MutableList<String>? {
        var list: ArrayList<Pair<String, MutableList<String>>> =
            IO_updateClass.getSavedFile(context)

        list.forEach {
            var randomList = list[Random.nextInt(0, list.size)].second
            if (randomList.size != 0) {
                return randomList
            }
        }
        return null
    }
}