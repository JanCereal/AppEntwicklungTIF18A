package com.example.AppEntwicklungTIF18A

import android.content.Context
import com.beust.klaxon.JsonObject
import org.json.JSONObject
import java.io.File

class IO_updateClass {
    companion object {
        var categoryCollection = ArrayList<Pair<String, MutableList<String>>>()

        fun addSingleCategoryWord(context: Context?, categoryName: String, addedWord: String) {
            var data = readCategoryJson(context)
            val oldValues = data?.get(categoryName)?.toString() + "," + addedWord
            data?.put(categoryName, oldValues)
            writeJsonData(context, data)
        }

        fun deleteSingleCategoryWord(context: Context, categoryName: String, deletedWord: String) {

        }

        fun updateSavedFile(
            context: Context?,
            toSave: ArrayList<Pair<String, MutableList<String>>>
        ) {
            //TODO update die hinterlegte File?

        }

        fun getSavedFile(context: Context?): ArrayList<Pair<String, MutableList<String>>> {
            val data = readCategoryJson(context)
            categoryCollection.clear()
            data?.keys()?.forEach { categoryName ->
                var words = ""
                data[categoryName].let { any -> words += any }
                words = words.replace(Regex("""[$\[\]\"]"""), "")
                val listWords = words.split(',')
                categoryCollection.add(Pair(categoryName, listWords as MutableList<String>))
            }
            return categoryCollection
        }

        private fun writeJsonData(context: Context?, data: JSONObject?) {
            try {
                val fileName = "categoryData.json"
                context?.openFileOutput(fileName, Context.MODE_PRIVATE).use { output ->
                    output?.write(data.toString().toByteArray())
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        fun writeCategoryJson(context: Context?) {
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