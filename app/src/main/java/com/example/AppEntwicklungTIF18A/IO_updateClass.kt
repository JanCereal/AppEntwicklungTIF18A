package com.example.AppEntwicklungTIF18A

import android.content.Context
import android.provider.MediaStore
import org.json.JSONObject
import java.io.File

class IO_updateClass {
    companion object {
        private const val FILE_CONST = "categoryData.json"
        private const val STATS_CONST = "statsData.json"
        private const val KEYWORD_CONST = "<--KeyWord-->Empty_Word<--KeyWord-->"
        private var categoryCollection = ArrayList<Pair<String, MutableList<String>>>()
        private var statsCollection = ArrayList<Pair<String,Int>>()

        fun writeHistory(context: Context?, categoryName: String?, combo:Int?){
            var data = readJson(context, STATS_CONST)
            data?.put(categoryName, combo)
            writeJsonData(context, data)
        }

        fun addSingleCategoryWord(context: Context?, categoryName: String, addedWord: String) {
            var data = readJson(context, FILE_CONST)
            val oldValues = data?.get(categoryName).toString()
                .trimEnd(']') + "," + "\"" + addedWord + "\"" + "]"
            data?.put(categoryName, oldValues)
            writeJsonData(context, data)
        }

        fun deleteSingleCategoryWord(context: Context?, categoryName: String, deletedWord: String) {
            var data = readJson(context, FILE_CONST)
            var oldValues = data?.get(categoryName)?.toString()?.replace("\"$deletedWord\",", "")
            oldValues = oldValues?.replace(",\"$deletedWord\"", "")
            oldValues = oldValues?.replace("\"$deletedWord\"", "")
            oldValues = oldValues?.trim(',')
            data?.put(categoryName, oldValues)
            writeJsonData(context, data)
        }

        fun addCategory(context: Context?, categoryName: String) {
            var data = readJson(context, FILE_CONST)
            data?.put(categoryName, KEYWORD_CONST)
            writeJsonData(context, data)
        }

        fun deleteCategory(context: Context?, categoryName: String) {
            var data = readJson(context, FILE_CONST)
            data?.remove(categoryName)
            writeJsonData(context, data)
        }

        fun getStats(context: Context?): ArrayList<Pair<String,Int>>{
            val data = readJson(context, STATS_CONST)
            statsCollection.clear()
            data?.keys()?.forEach { it ->
                statsCollection.add(Pair(it, data[it] as Int))
            }
            return statsCollection
        }

        fun getSavedFile(context: Context?): ArrayList<Pair<String, MutableList<String>>> {
            val data = readJson(context, FILE_CONST)
            categoryCollection.clear()
            data?.keys()?.forEach { categoryName ->
                var words = ""
                data[categoryName].let { any -> words += any }
                words = words.replace(Regex("""[$\[\]\"]"""), "")
                val listWords = words.split(',').toMutableList()
                listWords.remove(KEYWORD_CONST)
                categoryCollection.add(Pair(categoryName, listWords))
            }
            return categoryCollection
        }

        fun deleteFile(context: Context?){
           File(context?.filesDir?.absolutePath, FILE_CONST).delete()
        }

        fun writeCategoryJson(context: Context?) {
            //TODO convert was auch immer wir für ne liste benutzen zu Map ??
            var file = File(context?.filesDir?.absolutePath, FILE_CONST)
            if (!file.exists()) {
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
            var statsFile = File(context?.filesDir?.absolutePath, STATS_CONST)
            if (!statsFile.exists()) {
                try {
                    var js = JSONObject().toString()
                    context?.openFileOutput(FILE_CONST, Context.MODE_PRIVATE).use { output ->
                        output?.write(js.toByteArray())
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

        //region HelpMethods
        private fun writeJsonData(context: Context?, data: JSONObject?) {
            try {
                context?.openFileOutput(FILE_CONST, Context.MODE_PRIVATE).use { output ->
                    output?.write(data.toString().toByteArray())
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

         private fun readJson(context: Context?, file:String): JSONObject? {
            var jsonObject: JSONObject? = null
            context?.openFileInput(file).use { stream ->
                stream?.bufferedReader().use {
                    val data = JSONObject(it?.readText())
                    jsonObject = data
                }
            }
            return jsonObject
        }

        //endregion
    }
}