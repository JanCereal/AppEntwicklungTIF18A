package com.example.AppEntwicklungTIF18A

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Switch
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import com.example.AppEntwicklungTIF18A.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentSettingsBinding.inflate(layoutInflater)

        switchCheck(binding.switchCountClue, R.string.wordHint)
        switchCheck(binding.switchSound, R.string.sounds)
        switchDarkCheck(binding.switchDarkmode, R.string.darkmode)

        return binding.root
    }

    /**
     * Überprüft und setzt Switch Status
     */
    private fun switchCheck(switch: Switch, key :Int){
        val sharedPref = activity?.getSharedPreferences(getString(key), Context.MODE_PRIVATE)
        switch.isChecked = sharedPref != null && sharedPref.getString(key.toString(), "").equals("1")

        switch.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                // The switch is enabled
                if (sharedPref != null) {
                    with(sharedPref.edit()) {
                        putString(key.toString(), "1" )
                        apply()
                    }
                }
            } else {
                // The switch is disabled
                if (sharedPref != null) {
                    with(sharedPref.edit()) {
                        putString(key.toString(), "0" )
                        apply()
                    }
                }
            }
        }
    }

    /**
     * Überprüft und setzt Switch Status für den Darkmode
     */
    private fun switchDarkCheck(switch: Switch, key :Int){
        val sharedPref = activity?.getSharedPreferences(getString(key), Context.MODE_PRIVATE)
        switch.isChecked = sharedPref != null && sharedPref.getString(key.toString(), "").equals("1")

        switch.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                // The switch is enabled
                if (sharedPref != null) {
                    with(sharedPref.edit()) {
                        putString(key.toString(), "1" )
                        apply()
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    }
                }
            } else {
                // The switch is disabled
                if (sharedPref != null) {
                    with(sharedPref.edit()) {
                        putString(key.toString(), "0" )
                        apply()
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    }
                }
            }
        }
    }

}