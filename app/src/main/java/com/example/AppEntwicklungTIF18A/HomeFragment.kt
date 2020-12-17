package com.example.AppEntwicklungTIF18A

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
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

        IO_updateClass.writeFiles(context)
        val binding = FragmentHomeBinding.inflate(layoutInflater)

        binding.btnQuickplay.setOnClickListener { view: View ->
            val list = getRandomKeywordList()
            val mistakes = 0
            val bundle = bundleOf("selectedCategory" to list?.second, "categoryName" to list?.first, "Mistakes" to mistakes)
            view.findNavController().navigate(R.id.action_homeFragment_to_gameFragment, bundle)
        }

        //region DarkmodeCheck
        val sharedPrefHint = activity?.getSharedPreferences(getString(R.string.darkmode), Context.MODE_PRIVATE)
        if (sharedPrefHint != null && sharedPrefHint.getString(R.string.darkmode.toString(), "").equals("1")) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
        //endregion

        return binding.root
    }

    private fun getRandomKeywordList(): Pair<String, MutableList<String>>? {
        val list: ArrayList<Pair<String, MutableList<String>>> = IO_updateClass.getSavedFile(context)

        list.forEach { _ ->
            val random = Random.nextInt(0, list.size)
            val listName = list[random].first
            val randomList = list[random].second
            if (randomList.size != 0) {
                return Pair(listName, randomList)
            }
        }
        return null
    }
}