package com.example.AppEntwicklungTIF18A

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import com.example.AppEntwicklungTIF18A.databinding.FragmentStatsBinding

class StatsFragment : Fragment() {
   override fun onCreateView(
      inflater: LayoutInflater,
      container: ViewGroup?,
      savedInstanceState: Bundle?
   ): View {
      val binding = FragmentStatsBinding.inflate(layoutInflater)
      val statsArray = IOClass.getStats(requireContext())
      val statEntries = mutableListOf<String>()

      statsArray.sortBy { it.second }

      //region Anzeigen von variablem Text im StatFragment
      statsArray.forEach { entry ->
         val listSize = (IOClass.getCategoryLength(requireContext(), entry.first)) as Int
         val mistakes = entry.second.trim().toFloat()
         val mistakePercentage = (mistakes / listSize) * 100

         statEntries.add(entry.first + " with " + entry.second  + " Mistakes out of " + IOClass.getCategoryLength(requireContext(), entry.first) + " Words \n" +  mistakePercentage + "% mistake percentage" )
      }

      val adapter = ArrayAdapter(requireContext(), R.layout.template_stat_entry, statEntries)
      binding.listStats.adapter = adapter

      if (statEntries.isEmpty()) {
         binding.txtHighScore.text = "Highscore pending..."
      } else {
         binding.txtHighScore.text = "Highscore\n" + statEntries[0]
      }
      //endregion

      //Listener
      binding.txtDeleteData.setOnClickListener { v ->
         IOClass.deleteStatsFile(requireContext())
         adapter.clear()

         IOClass.writeFiles(requireContext())
         binding.txtHighScore.text = "Highscore pending..."
      }

      return binding.root
   }
}