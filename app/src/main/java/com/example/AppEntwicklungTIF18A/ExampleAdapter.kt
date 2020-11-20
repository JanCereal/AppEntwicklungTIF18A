package com.example.AppEntwicklungTIF18A

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ExampleAdapter(exampleList : ArrayList<ExampleCategory>): RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder>() {
    var mExampleCategories = exampleList

    inner class ExampleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image = itemView.findViewById<ImageView>(R.id.imgView)
        var categoryName = itemView.findViewById<TextView>(R.id.txtCategory)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExampleViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.example_category, parent, false)
        return ExampleViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mExampleCategories.size
    }

    override fun onBindViewHolder(holder: ExampleViewHolder, position: Int) {
        val currentItem = mExampleCategories.get(position)

        holder.image.setImageResource(currentItem.getCategoryImage())
        holder.categoryName.setText(currentItem.getCategoryName())
    }
}