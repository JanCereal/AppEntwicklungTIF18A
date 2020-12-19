package com.example.AppEntwicklungTIF18A

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.AppEntwicklungTIF18A.databinding.FragmentSelectedcategoryBinding
import kotlinx.android.synthetic.main.fragment_selectedcategory.*

class SelectedCategoryFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSelectedcategoryBinding.inflate(layoutInflater)
        val inputManager = context?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        val categoryName = arguments?.get("editCategoryName") as String
        val categoryList = arguments?.get("editCategoryList") as ArrayList<String>

        (activity as AppCompatActivity).supportActionBar?.title = categoryName

        val recyclerView = binding.recyclerViewMember
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = MemberAdapter(categoryList, container, categoryName)

        /**
         * Methode zum Hinzufügen eines Wortes zu einer Kategorie
         */
        fun addWordToCategory(view: View) {
            categoryList.forEach {
                // Duplikatscheck
                if (txtAddMember.text.toString().toLowerCase().trim() == it.toLowerCase()) {
                    Toast.makeText(context, "Word already existing!", Toast.LENGTH_LONG).show()

                    // Tastaur verstecken
                    inputManager.hideSoftInputFromWindow(view.windowToken, 0)
                    txtAddMember.text.clear()
                    return
                }
            }
            // Wort der Liste und der Savefile hinzufügen
            categoryList.add(txtAddMember.text.toString().trim())
            IOClass.addWordToCategory(context, categoryName , txtAddMember.text.toString().trim())

            recyclerView.adapter = MemberAdapter(categoryList, container, categoryName)

            inputManager.hideSoftInputFromWindow(view.windowToken, 0)
            txtAddMember.text.clear()
        }

        // Hinzufügen bei Buttonclick
        binding.btnAddMember.setOnClickListener { view: View ->
            addWordToCategory(view)
        }

        // Hinzufügen bei ENTER
        binding.txtAddMember.setOnKeyListener(View.OnKeyListener { view, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                addWordToCategory(view)
                return@OnKeyListener true
            }
            false
        })

        // Button disabled, wenn Eingabefeld leer
        binding.txtAddMember.doAfterTextChanged {
            binding.btnAddMember.isEnabled = binding.txtAddMember.text.trim().isNotEmpty()
        }

        return binding.root
    }
}