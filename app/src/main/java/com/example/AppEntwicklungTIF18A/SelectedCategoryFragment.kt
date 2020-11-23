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
import com.example.AppEntwicklungTIF18A.databinding.FragmentSelectedcategoryBinding
import kotlinx.android.synthetic.main.fragment_selectedcategory.*

class SelectedCategoryFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentSelectedcategoryBinding.inflate(layoutInflater)
        val memberList = arguments?.getStringArrayList("editCategory")

        val recyclerView = binding.recyclerViewMember
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = MemberAdapter(memberList as kotlin.collections.ArrayList<String>)

        binding.btnAddMember.setOnClickListener { v: View ->
            memberList.add(txtAddMember.text.toString())
            recyclerView.adapter = MemberAdapter(memberList)
        }

        return binding.root
    }
}