package com.example.AppEntwicklungTIF18A

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class ExampleAdapter(exampleList : ArrayList<Category>): RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder>() {
    var categories = exampleList

    inner class ExampleViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        var categoryImage = itemView.findViewById<ImageView>(R.id.imgCategory)
        var categoryName = itemView.findViewById<TextView>(R.id.txtCategory)
        var deleteImage = itemView.findViewById<ImageView>(R.id.imgDelete)
        var playImage = itemView.findViewById<ImageView>(R.id.imgPlay)

        init {
            deleteImage.setOnClickListener{v: View ->
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    categories.removeAt(position)
                    notifyItemRemoved(position)
                }
            }
            playImage.setOnClickListener{ v: View ->
                val  position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    Toast.makeText(itemView.context, "${categories[position].getCategoryList()}", Toast.LENGTH_LONG).show()
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
        holder.categoryImage.setImageResource(categories[position].getCategoryImage())
        holder.categoryName.text = categories[position].getCategoryName()
    }
}