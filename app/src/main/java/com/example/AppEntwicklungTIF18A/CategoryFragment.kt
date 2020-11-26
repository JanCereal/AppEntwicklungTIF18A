package com.example.AppEntwicklungTIF18A

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.AppEntwicklungTIF18A.databinding.FragmentCategoryBinding
import kotlinx.android.synthetic.main.fragment_category.*

class CategoryFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentCategoryBinding.inflate(layoutInflater)
        val inputManager = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val categories = ArrayList<Pair<String, MutableList<String>>>()
        
        categories.add(Pair("Nature", mutableListOf("Tree", "Desert", "Forest")))
        categories.add(Pair("Cars", mutableListOf("Audi", "Mercedes", "Opel")))
        categories.add(Pair("Brands", mutableListOf("Adidas", "Nike", "Puma")))

        val recyclerView = binding.recyclerViewCategory
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = CategoryAdapter(categories)

        binding.txtAddCategory.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                AddCategory(categories, recyclerView, v, inputManager)
                return@OnKeyListener true
            }
            false
        })
        binding.btnCreateCategory.setOnClickListener{ v : View ->
            AddCategory(categories, recyclerView, v, inputManager)
        }
        return binding.root
    }

    fun AddCategory(categories: ArrayList<Pair<String, MutableList<String>>>, recyclerView: RecyclerView, v: View, inputManager: InputMethodManager) {
        categories.add(Pair(txtAddCategory.text.toString().trim(), mutableListOf<String>()))
        recyclerView.adapter = CategoryAdapter(categories)

        inputManager.hideSoftInputFromWindow(v.windowToken, 0)

        txtAddCategory.text.clear()
    }
}