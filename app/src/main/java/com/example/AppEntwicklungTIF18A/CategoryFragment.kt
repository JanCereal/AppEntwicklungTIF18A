package com.example.AppEntwicklungTIF18A

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
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
        val categories = IOClass.getSavedFile(context)

        val recyclerView = binding.recyclerViewCategory
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = CategoryAdapter(categories, container)

        /**
         * Methode zum Hinzufügen einer Kategorie
         */
        fun addCategory(v: View) {
            categories.forEach {
                // Duplikatscheck
                if (txtAddCategory.text.toString().toLowerCase().trim() == it.first.toLowerCase()) {
                    Toast.makeText(context, "Category already existing!", Toast.LENGTH_LONG).show()

                    inputManager.hideSoftInputFromWindow(v.windowToken, 0)
                    txtAddCategory.text.clear()
                    return
                }
            }
            categories.add(Pair(txtAddCategory.text.toString().trim(), mutableListOf<String>()))
            IOClass.addCategory(context, txtAddCategory.text.toString().trim())

            recyclerView.adapter = CategoryAdapter(categories, container)

            inputManager.hideSoftInputFromWindow(v.windowToken, 0)
            txtAddCategory.text.clear()
        }

        // Hinzufügen bei ENTER
        binding.txtAddCategory.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                addCategory(v)
                return@OnKeyListener true
            }
            false
        })

        // Hinzufügen bei Buttonclick
        binding.btnCreateCategory.setOnClickListener{ v : View ->
            addCategory(v)
        }

        // Button disabled, wenn Eingabefeld leer
        binding.txtAddCategory.doAfterTextChanged {
            binding.btnCreateCategory.isEnabled = binding.txtAddCategory.text.trim().isNotEmpty()
        }

        return binding.root
    }
}