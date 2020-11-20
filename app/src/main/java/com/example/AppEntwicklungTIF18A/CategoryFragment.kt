package com.example.AppEntwicklungTIF18A

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
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
        categories.add(Category(R.drawable.ic_android, "Nature"))
        categories.add(Category(R.drawable.ic_android, "Cars"))
        categories.add(Category(R.drawable.ic_android, "Brands"))

        val recyclerView = binding.recyclerview
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = ExampleAdapter(categories)

        return binding.root
    }
}