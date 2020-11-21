package com.example.AppEntwicklungTIF18A

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.AppEntwicklungTIF18A.databinding.FragmentCategoryBinding

class CategoryFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCategoryBinding.inflate(layoutInflater)

        val categories = ArrayList<Category>()
        categories.add(Category(R.drawable.ic_android, "Nature", mutableListOf("Tree", "Desert", "Forest")))
        categories.add(Category(R.drawable.ic_android, "Cars", mutableListOf("Audi", "Mercedes", "Opel")))
        categories.add(Category(R.drawable.ic_android, "Brands", mutableListOf("Adidas", "Nike", "Puma")))

        val recyclerView = binding.recyclerview
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = ExampleAdapter(categories)

        binding.fabAdd.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_categoryFragment_to_selectedCategoryFragment)
        }

        return binding.root
    }
}