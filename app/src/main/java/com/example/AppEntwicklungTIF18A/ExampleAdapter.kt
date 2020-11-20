package com.example.AppEntwicklungTIF18A

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ExampleAdapter(exampleList : ArrayList<Category>): RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder>() {
    var categories = exampleList

    inner class ExampleViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        var image = itemView.findViewById<ImageView>(R.id.imgView)
        var categoryName = itemView.findViewById<TextView>(R.id.txtCategory)
        var deleteImage = itemView.findViewById<ImageView>(R.id.imgDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.example_category, parent, false)
        return ExampleViewHolder(view)
    }

    override fun getItemCount(): Int {
        return categories.size
    }

    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        holder.image.setImageResource(categories[position].getCategoryImage())
        holder.categoryName.text = categories[position].getCategoryName()

        holder.itemView.setOnClickListener{
            if (categories.size > 0) {
                categories.removeAt(position)
                notifyItemRemoved(position)
            }
        }
    }
}