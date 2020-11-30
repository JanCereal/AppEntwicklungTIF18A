package com.example.AppEntwicklungTIF18A

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView

class CategoryAdapter(exampleList: ArrayList<Pair<String, MutableList<String>>>, parent: ViewGroup?) : RecyclerView.Adapter<CategoryAdapter.ExampleViewHolder>() {
    var categories = exampleList
    var context = parent?.context

    inner class ExampleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var categoryName = itemView.findViewById<TextView>(R.id.txtCategoryName)
        var deleteImage = itemView.findViewById<ImageView>(R.id.imgDelete)
        var playImage = itemView.findViewById<ImageView>(R.id.imgPlay)
        var editImage = itemView.findViewById<ImageView>(R.id.imgEdit)

        init {
            deleteImage.setOnClickListener { v: View ->
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    println(categoryName.text.toString())
                    IO_updateClass.deleteCategory(context, categoryName.text.toString())
                    categories.removeAt(position)
                    notifyItemRemoved(position)
                }
            }
            playImage.setOnClickListener { v: View ->
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val bundle = bundleOf("selectedCategory" to ArrayList<String>(categories[position].second))
                    v.findNavController().navigate(R.id.action_categoryFragment_to_gameFragment, bundle)
                }
            }
            editImage.setOnClickListener{ v: View ->
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val bundle = bundleOf("editCategoryName" to categories[position].first, "editCategoryList" to ArrayList<String>(categories[position].second))
                    v.findNavController().navigate(R.id.action_categoryFragment_to_selectedCategoryFragment, bundle)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.category_template, parent, false)
        return ExampleViewHolder(view)
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        holder.categoryName.text = categories[position].first
    }
}