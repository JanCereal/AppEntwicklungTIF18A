package com.example.AppEntwicklungTIF18A

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.AppEntwicklungTIF18A.databinding.FragmentSuccessBinding


class SuccessFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (activity as AppCompatActivity?)!!.supportActionBar!!.hide()
        val binding = FragmentSuccessBinding.inflate(layoutInflater)
        val categoryName = arguments?.getString("categoryName")
        val mistakes = arguments?.getInt("Mistakes")
        val tempKeywordList = arguments?.getStringArrayList("selectedCategory")
        val bundle = bundleOf("selectedCategory" to tempKeywordList,"categoryName" to categoryName,  "Mistakes" to mistakes)

        //Anzeigen von variablem Text im SuccessFragment
        if (tempKeywordList != null && tempKeywordList.isEmpty()) {
            when (mistakes) {
                1 -> binding.successTextView.text = "You guessed all words! \n With only " + mistakes.toString() + " mistake"
                else -> {
                    binding.successTextView.text = "You guessed all words! \n With only " + mistakes.toString() + " mistakes"
                }
            }
        }

        //region Navigation
        if (tempKeywordList?.size != 0) {
            binding.btnNext.setOnClickListener { view: View ->
                view.findNavController().navigate(R.id.action_successFragment_to_gameFragment, bundle)
                (activity as AppCompatActivity?)!!.supportActionBar!!.show()
            }
        } else {
            binding.btnNext.setOnClickListener { view: View ->
                IOClass.writeHistory(context, categoryName, mistakes)
                view.findNavController().navigate(R.id.action_successFragment_to_homeFragment)
                (activity as AppCompatActivity?)!!.supportActionBar!!.show()
            }
        }
        //endregion

        return binding.root
    }
}