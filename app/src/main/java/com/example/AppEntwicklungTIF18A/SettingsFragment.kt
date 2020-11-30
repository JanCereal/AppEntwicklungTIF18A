package com.example.AppEntwicklungTIF18A

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.ImageSwitcher
import android.widget.Switch
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

        //region SwitchChecks
        switchCheck(binding.switchCountClue, R.string.wordHint)
        switchCheck(binding.switchSound, R.string.sounds)
        switchCheck(binding.switchDarkmode, R.string.darkmode)
        switchCheck(binding.switchLanguage, R.string.language)
        //endregion

        return binding.root
    }

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

}