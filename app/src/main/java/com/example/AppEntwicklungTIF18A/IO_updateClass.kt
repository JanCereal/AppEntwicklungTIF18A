package com.example.AppEntwicklungTIF18A

import android.content.Context
import com.beust.klaxon.JsonObject
import org.json.JSONObject
import java.io.File

class IO_updateClass {
    companion object {
        private const val FILE_CONST = "categoryData.json"
        private var categoryCollection = ArrayList<Pair<String, MutableList<String>>>()

        fun addSingleCategoryWord(context: Context?, categoryName: String, addedWord: String) {
            var data = readCategoryJson(context)
            val oldValues = data?.get(categoryName).toString().trimEnd(']') + "," +"\"" +addedWord+ "\"" + "]"
            data?.put(categoryName, oldValues)
            writeJsonData(context, data)
        }

        fun deleteSingleCategoryWord(context: Context?, categoryName: String, deletedWord: String) {
            var data = readCategoryJson(context)
            var oldValues = data?.get(categoryName)?.toString()?.replace("\"$deletedWord\",","")
            oldValues = oldValues?.replace(",\"$deletedWord\"","")
            oldValues = oldValues?.replace("\"$deletedWord\"","")
            oldValues = oldValues?.trim(',')
            data?.put(categoryName, oldValues)
            writeJsonData(context, data)
        }

        fun addCategory(context: Context?, categoryName: String) {
            var data = readCategoryJson(context)
            data?.put(categoryName, "")
            writeJsonData(context, data)
        }

        fun deleteCategory(context: Context?, categoryName: String) {
            var data = readCategoryJson(context)
            data?.remove(categoryName)
            writeJsonData(context, data)
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
                context?.openFileOutput(FILE_CONST, Context.MODE_PRIVATE).use { output ->
                    output?.write(data.toString().toByteArray())
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        fun writeCategoryJson(context: Context?) {
            //TODO convert was auch immer wir für ne liste benutzen zu Map ??
            var file = File(context?.filesDir?.absolutePath, "categoryData.json")
            if (file.exists()){
                return;
            }

            var categoryMap: MutableMap<String, MutableList<String>> = mutableMapOf()
            categoryMap["Nature"] = mutableListOf("water", "tree", "dirt", "flower", "bird")

            var jsonObject = JSONObject(categoryMap as Map<String, MutableList<String>>).toString()

            try {
                context?.openFileOutput(FILE_CONST, Context.MODE_PRIVATE).use { output ->
                    output?.write(jsonObject.toByteArray())
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        private fun readCategoryJson(context: Context?): JSONObject? {
            var jsonObject: JSONObject? = null
            context?.openFileInput(FILE_CONST).use { stream ->
                stream?.bufferedReader().use {
                    val data = JSONObject(it?.readText())
                    jsonObject = data
                }
            }
            return jsonObject
        }
    }
}