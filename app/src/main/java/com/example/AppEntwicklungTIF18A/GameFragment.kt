package com.example.AppEntwicklungTIF18A

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.AppEntwicklungTIF18A.databinding.FragmentGameBinding
import kotlin.random.Random


class GameFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentGameBinding.inflate(layoutInflater)

        binding.btnNext.setOnClickListener { view: View ->
            val rnd = (0..3).random()
            if (rnd == 1) {
                binding.imagePanel1.setImageResource(com.example.AppEntwicklungTIF18A.R.drawable.test2)
                binding.imagePanel2.setImageResource(com.example.AppEntwicklungTIF18A.R.drawable.test2)
                binding.imagePanel3.setImageResource(com.example.AppEntwicklungTIF18A.R.drawable.test2)
                binding.imagePanel4.setImageResource(com.example.AppEntwicklungTIF18A.R.drawable.test2)
            } else {
                binding.imagePanel1.setImageResource(com.example.AppEntwicklungTIF18A.R.drawable.test)
                binding.imagePanel2.setImageResource(com.example.AppEntwicklungTIF18A.R.drawable.test)
                binding.imagePanel3.setImageResource(com.example.AppEntwicklungTIF18A.R.drawable.test)
                binding.imagePanel4.setImageResource(com.example.AppEntwicklungTIF18A.R.drawable.test)
            }
        }

        (activity as AppCompatActivity?)!!.supportActionBar!!.show()
        return binding.root
    }
}