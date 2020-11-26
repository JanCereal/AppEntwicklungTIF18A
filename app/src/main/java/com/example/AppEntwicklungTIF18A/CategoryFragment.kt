package com.example.AppEntwicklungTIF18A

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
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
        val categories = IO_updateClass.getSavedFile(context)

        val recyclerView = binding.recyclerViewCategory
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = CategoryAdapter(categories, container)

        binding.txtAddCategory.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                categories.forEach {
                    println(txtAddCategory.text.toString().toLowerCase())
                    println(it.first.toLowerCase())
                    if (txtAddCategory.text.toString().toLowerCase().trim() == it.first.toLowerCase()) {
                        Toast.makeText(context, "Category already existing!", Toast.LENGTH_LONG).show()
                        inputManager.hideSoftInputFromWindow(v.windowToken, 0)
                        txtAddCategory.text.clear()
                        return@OnKeyListener true
                    }
                }
                categories.add(Pair(txtAddCategory.text.toString().trim(), mutableListOf<String>()))
                recyclerView.adapter = CategoryAdapter(categories, container)
                IO_updateClass.addCategory(context, txtAddCategory.text.toString().trim())

                inputManager.hideSoftInputFromWindow(v.windowToken, 0)

                txtAddCategory.text.clear()
                return@OnKeyListener true
            }
            false
        })

        binding.btnCreateCategory.setOnClickListener{ v : View ->
            categories.add(Pair(txtAddCategory.text.toString().trim(), mutableListOf<String>()))
            recyclerView.adapter = CategoryAdapter(categories, container)
            IO_updateClass.addCategory(context, txtAddCategory.text.toString().trim())

            inputManager.hideSoftInputFromWindow(v.windowToken, 0)

            txtAddCategory.text.clear()
        }
        return binding.root
    }
}