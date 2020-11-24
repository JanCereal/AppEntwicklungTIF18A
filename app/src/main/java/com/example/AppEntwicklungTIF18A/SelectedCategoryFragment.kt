package com.example.AppEntwicklungTIF18A

import android.content.Context
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
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
        val categoryName = arguments?.get("editCategoryName")
        val categoryList = arguments?.get("editCategoryList")

        (activity as AppCompatActivity).supportActionBar?.title = categoryName.toString()

        val recyclerView = binding.recyclerViewMember
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = MemberAdapter(categoryList as ArrayList<String>)

        binding.btnAddMember.setOnClickListener { v: View ->
            AddMember(categoryList, recyclerView, v, inputManager)
        }

        binding.txtAddMember.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                AddMember(categoryList, recyclerView, v, inputManager)
                return@OnKeyListener true
            }
            false
        })
        return binding.root
    }

    fun AddMember(categoryList: ArrayList<String>, recyclerView: RecyclerView, v: View, inputManager: InputMethodManager) {
        categoryList.add(txtAddMember.text.toString().trim())
        recyclerView.adapter = MemberAdapter(categoryList)

        inputManager.hideSoftInputFromWindow(v.windowToken, 0)

        txtAddMember.text.clear()
    }
}