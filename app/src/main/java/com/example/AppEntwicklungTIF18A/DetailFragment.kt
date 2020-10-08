package com.example.AppEntwicklungTIF18A

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.AppEntwicklungTIF18A.databinding.FragmentDetailBinding

class DetailFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentDetailBinding.inflate(layoutInflater)
        binding.EditButton.setOnClickListener {view: View ->
            view.findNavController().navigate(R.id.action_detailFragment_to_editFragment)
        }
        // Setze Titel auf den Wert, der beim Navigieren übergeben
        binding.textView4.text = arguments?.getString("noteTitle")
        return binding.root
    }
}