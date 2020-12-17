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
        fun localAddMethod(v: View) {
            categories.add(Pair(txtAddCategory.text.toString().trim(), mutableListOf<String>()))
            recyclerView.adapter = CategoryAdapter(categories, container)
            IOClass.addCategory(context, txtAddCategory.text.toString().trim())

            inputManager.hideSoftInputFromWindow(v.windowToken, 0)

            txtAddCategory.text.clear()
        }

        //region Listener und EnterCheck
        binding.txtAddCategory.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                categories.forEach {
                    if (txtAddCategory.text.toString().toLowerCase().trim() == it.first.toLowerCase()) {
                        Toast.makeText(context, "Category already existing!", Toast.LENGTH_LONG).show()

                        inputManager.hideSoftInputFromWindow(v.windowToken, 0)
                        txtAddCategory.text.clear()
                        return@OnKeyListener true
                    }
                }
                localAddMethod(v)
                return@OnKeyListener true
            }
            false
        })

        binding.btnCreateCategory.setOnClickListener{ v : View ->
            localAddMethod(v)
        }

        binding.txtAddCategory.doAfterTextChanged {
            binding.btnCreateCategory.isEnabled = binding.txtAddCategory.text.trim().isNotEmpty()
        }
        //endregion

        return binding.root
    }
}