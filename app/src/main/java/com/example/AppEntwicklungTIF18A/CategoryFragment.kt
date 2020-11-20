package com.example.AppEntwicklungTIF18A

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class CategoryFragment : Fragment() {
    private var mlayoutManager : RecyclerView.LayoutManager? = null
    private var madapter : RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder>? = null
    private var mrecyclerView : RecyclerView? = null


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        categoriesList()
        buildRecyclerview()

        return view
    }

    fun categoriesList() {
        val categories = ArrayList<Category>()
        categories.add(Category(R.drawable.ic_android, "Nature"))
        categories.add(Category(R.drawable.ic_android, "Cars"))
        categories.add(Category(R.drawable.ic_android, "Brands"))
    }

    fun buildRecyclerview() {
        mrecyclerView?.findViewById<RecyclerView>(R.id.recyclerview)
        mrecyclerView?.setHasFixedSize(true)
        mlayoutManager = LinearLayoutManager(context)
        madapter = ExampleAdapter(categories)

        mrecyclerView?.layoutManager = mlayoutManager
        mrecyclerView?.adapter = madapter

        madapter.set
    }
}