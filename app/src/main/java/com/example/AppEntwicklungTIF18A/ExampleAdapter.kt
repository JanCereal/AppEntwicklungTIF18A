package com.example.AppEntwicklungTIF18A

import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView

class ExampleAdapter(exampleList: ArrayList<Category>) : RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder>() {
    var categories = exampleList

    inner class ExampleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var categoryImage = itemView.findViewById<ImageView>(R.id.imgCategory)
        var categoryName = itemView.findViewById<TextView>(R.id.txtCategory)
        var deleteImage = itemView.findViewById<ImageView>(R.id.imgDelete)
        var playImage = itemView.findViewById<ImageView>(R.id.imgPlay)
        var editImage = itemView.findViewById<ImageView>(R.id.imgEdit)

        init {
            deleteImage.setOnClickListener { v: View ->
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    categories.removeAt(position)
                    notifyItemRemoved(position)
                }
            }
            playImage.setOnClickListener { v: View ->
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val position = adapterPosition
                    val bundle = bundleOf("selectedCategory" to categories[position].getCategoryList())
                    v.findNavController().navigate(R.id.action_categoryFragment_to_gameFragment, bundle)
                }
            }
            editImage.setOnClickListener{ v: View ->
                val position = adapterPosition
                val bundle = bundleOf("editCategory" to categories[position].getCategoryList())
                v.findNavController().navigate(R.id.action_categoryFragment_to_selectedCategoryFragment, bundle)
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
        holder.categoryImage.setImageResource(categories[position].getCategoryImage())
        holder.categoryName.text = categories[position].getCategoryName()
    }
}