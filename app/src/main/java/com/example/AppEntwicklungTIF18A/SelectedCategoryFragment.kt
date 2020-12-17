package com.example.AppEntwicklungTIF18A

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
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
         * Methode zum Hinzufügen einer Kategorie
         */
        fun localAddMethod(v: View){
            categoryList.add(txtAddMember.text.toString().trim())
            recyclerView.adapter = MemberAdapter(categoryList, container, categoryName)

            IOClass.addSingleCategoryWord(context, categoryName , txtAddMember.text.toString().trim())

            inputManager.hideSoftInputFromWindow(v.windowToken, 0)
            txtAddMember.text.clear()
        }

        //region Listener
        binding.btnAddMember.setOnClickListener { v: View ->
            localAddMethod(v)
        }

        binding.txtAddMember.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                localAddMethod(v)
                return@OnKeyListener true
            }
            false
        })

        binding.txtAddMember.doAfterTextChanged {
            binding.btnAddMember.isEnabled = binding.txtAddMember.text.trim().isNotEmpty()
        }
        //endregion

        return binding.root
    }
}