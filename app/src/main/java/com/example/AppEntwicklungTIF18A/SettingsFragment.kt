package com.example.AppEntwicklungTIF18A

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.AppEntwicklungTIF18A.databinding.FragmentSettingsBinding
import kotlinx.android.synthetic.main.fragment_game.*
import kotlinx.android.synthetic.main.fragment_settings.*

class SettingsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSettingsBinding.inflate(layoutInflater)

        val sharedPrefHint = activity?.getSharedPreferences(getString(R.string.wordHint), Context.MODE_PRIVATE)
        //switch startValue check
        binding.switchCountClue.isChecked = sharedPrefHint != null && sharedPrefHint.getString(R.string.wordHint.toString(), "").equals("1")

        binding.switchCountClue.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                // The switch is enabled
                if (sharedPrefHint != null) {
                    with(sharedPrefHint.edit()) {
                        putString(R.string.wordHint.toString(), "1" )
                        apply()
                    }
                }
            } else {
                // The switch is disabled
                if (sharedPrefHint != null) {
                    with(sharedPrefHint.edit()) {
                        putString(R.string.wordHint.toString(), "0" )
                        apply()
                    }
                }
            }
        }
        return binding.root
    }
}