package com.example.AppEntwicklungTIF18A

import android.content.Context
import org.json.JSONObject
import java.io.File

class IO_updateClass {
    companion object {
        var categoryCollection = ArrayList<Category>()

        fun updateCategory(context: Context?, changedCategory: ArrayList<String>) {
            writeCategoryJson(context, changedCategory)
        }


        fun updateSavedFile(context: Context?, toSave: ArrayList<Category>) {
            //TODO update die hinterlegte File?
        }

        fun getSavedFile(context: Context?): ArrayList<Category> {
            val data = readCategoryJson(context)
            categoryCollection.clear()
            data?.keys()?.forEach {categoryName ->
                var words = ""
                data[categoryName].let { any -> words += any }
                words = words.replace(Regex("""[$\[\]\"]"""), "")
                val listWords = words.split(',')
                val newCategory = Category(R.drawable.ic_android, categoryName, listWords as MutableList<String>)
                categoryCollection.add(newCategory)
            }
            categoryCollection.forEach {
                println(it._categoryName)
                println(it._categoryMembers)
            }
            return categoryCollection
        }

        private fun writeCategoryJson(context: Context?, changedCategory: ArrayList<String>) {
            //TODO convert was auch immer wir für ne liste benutzen zu Map
            var categoryMap: MutableMap<String, MutableList<String>> = mutableMapOf()
            categoryMap["Nature"] = mutableListOf("water", "tree", "dirt", "flower", "bird")


            val file = File(context?.filesDir, "categoryData")

            var jsonString = JSONObject(categoryMap as Map<String, MutableList<String>>).toString()

            try {
                val fileName = "categoryData.json"
                context?.openFileOutput(fileName, Context.MODE_PRIVATE).use { output ->
                    output?.write(jsonString.toByteArray())
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        private fun readCategoryJson(context: Context?): JSONObject? {
            var jsonObject: JSONObject? = null
            context?.openFileInput("categoryData.json").use { stream ->
                stream?.bufferedReader().use {
                    val data = JSONObject(it?.readText())
                    jsonObject = data
                }
            }
            return jsonObject
        }
    }
}