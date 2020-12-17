package com.example.AppEntwicklungTIF18A

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView

class CategoryAdapter(exampleList: ArrayList<Pair<String, MutableList<String>>>, parent: ViewGroup?) : RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder>() {
   var categories = exampleList
   var context = parent?.context

   inner class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
      var categoryName = itemView.findViewById<TextView>(R.id.txtCategoryName)
      private var deleteImage = itemView.findViewById<ImageView>(R.id.imgDelete)
      private var playImage = itemView.findViewById<ImageView>(R.id.imgPlay)
      private var editImage = itemView.findViewById<ImageView>(R.id.imgEdit)

      init {
         deleteImage.setOnClickListener { v: View ->
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
               IO_updateClass.deleteCategory(context, categoryName.text.toString())
               categories.removeAt(position)
               notifyItemRemoved(position)
            }
         }
         playImage.setOnClickListener { v: View ->
            val position = adapterPosition
            val mistakes = 0
            if (position != RecyclerView.NO_POSITION) {
               if (categories[position].second.isEmpty()) {
                  Toast.makeText(context, "Selected Category is empty!", Toast.LENGTH_LONG).show()
               } else {
                  val bundle = bundleOf("selectedCategory" to ArrayList<String>(categories[position].second), "categoryName" to categories[position].first, "Mistakes" to mistakes)
                  v.findNavController().navigate(R.id.action_categoryFragment_to_gameFragment, bundle)
               }
            }
         }
         editImage.setOnClickListener { v: View ->
            val position = adapterPosition
            if (position != RecyclerView.NO_POSITION) {
               val bundle = bundleOf("editCategoryName" to categories[position].first, "editCategoryList" to ArrayList<String>(categories[position].second))
               v.findNavController().navigate(R.id.action_categoryFragment_to_selectedCategoryFragment, bundle)
            }
         }
      }
   }

   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
      val view = LayoutInflater.from(parent.context).inflate(R.layout.template_category, parent, false)
      return CategoryViewHolder(view)
   }

   override fun getItemCount(): Int {
      return categories.size
   }

   override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
      holder.categoryName.text = categories[position].first
   }
}