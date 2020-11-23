package com.example.AppEntwicklungTIF18A

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.AppEntwicklungTIF18A.databinding.FragmentCategoryBinding

class CategoryFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCategoryBinding.inflate(layoutInflater)

        val categories = ArrayList<Category>()
        categories.add(Category("Nature", mutableListOf("Tree", "Desert", "Forest")))
        categories.add(Category("Cars", mutableListOf("Audi", "Mercedes", "Opel")))
        categories.add(Category("Brands", mutableListOf("Adidas", "Nike", "Puma")))

        val recyclerView = binding.recyclerViewCategory
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = CategoryAdapter(categories)

        return binding.root
    }
}