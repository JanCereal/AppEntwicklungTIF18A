package com.example.AppEntwicklungTIF18A

import android.content.Context
import org.json.JSONObject
import java.io.File

class IO_updateClass {
    companion object {
        var categoryCollection = ArrayList<Pair<String, MutableList<String>>>()

        fun updateCategory(context: Context?, changedCategory: ArrayList<String>) {
            writeCategoryJson(context, changedCategory)
        }


        fun updateSavedFile(context: Context?, toSave: ArrayList<Pair<String,MutableList<String>>>) {
            //TODO update die hinterlegte File?
        }

        fun getSavedFile(context: Context?): ArrayList<Pair<String,MutableList<String>>> {
            val data = readCategoryJson(context)
            categoryCollection.clear()
            data?.keys()?.forEach {categoryName ->
                var words = ""
                data[categoryName].let { any -> words += any }
                words = words.replace(Regex("""[$\[\]\"]"""), "")
                val listWords = words.split(',')
                categoryCollection.add(Pair(categoryName,listWords as MutableList<String>))
            }
           return categoryCollection
        }

        private fun writeCategoryJson(context: Context?, changedCategory: ArrayList<String>) {
            //TODO convert was auch immer wir für ne liste benutzen zu Map
            var categoryMap: MutableMap<String, MutableList<String>> = mutableMapOf()
            categoryMap["Nature"] = mutableListOf("water", "tree", "dirt", "flower", "bird")

            var jsonObject = JSONObject(categoryMap as Map<String, MutableList<String>>).toString()

            try {
                val fileName = "categoryData.json"
                context?.openFileOutput(fileName, Context.MODE_PRIVATE).use { output ->
                    output?.write(jsonObject.toByteArray())
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