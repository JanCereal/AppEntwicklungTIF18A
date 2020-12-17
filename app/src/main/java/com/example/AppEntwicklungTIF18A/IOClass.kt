package com.example.AppEntwicklungTIF18A

import android.content.Context
import org.json.JSONObject
import java.io.File

class IOClass {
    companion object {
        private const val FILE_CONST = "categoryData.json"
        private const val STATS_CONST = "statsData.json"
        private const val KEYWORD_CONST = "<--KeyWord-->Empty_Word<--KeyWord-->"
        private var categoryCollection = ArrayList<Pair<String, MutableList<String>>>()
        private var statsCollection = ArrayList<Pair<String,String>>()

        fun writeHistory(context: Context?, categoryName: String?, mistakes:Int?){
            val data = readJson(context, STATS_CONST)
            data?.put(data.length().toString(),  Pair(categoryName, mistakes))
            writeJsonData(context, STATS_CONST, data)
        }

        fun getStats(context: Context?): ArrayList<Pair<String,String>>{
            val data = readJson(context, STATS_CONST)
            statsCollection.clear()
            data?.keys()?.forEach { it ->
                println(data)
                val value = data[it].toString().trim('(').trim(')').split(',')
                statsCollection.add(Pair(value[0], value[1]))
            }
            return statsCollection
        }

        fun addSingleCategoryWord(context: Context?, categoryName: String, addedWord: String) {
            val data = readJson(context, FILE_CONST)
            val oldValues = data?.get(categoryName).toString()
                .trimEnd(']') + "," + "\"" + addedWord + "\"" + "]"
            data?.put(categoryName, oldValues)
            writeJsonData(context,FILE_CONST, data)
        }

        fun deleteSingleCategoryWord(context: Context?, categoryName: String, deletedWord: String) {
            val data = readJson(context, FILE_CONST)
            var oldValues = data?.get(categoryName)?.toString()?.replace("\"$deletedWord\",", "")
            oldValues = oldValues?.replace(",\"$deletedWord\"", "")
            oldValues = oldValues?.replace("\"$deletedWord\"", "")
            oldValues = oldValues?.trim(',')
            data?.put(categoryName, oldValues)
            writeJsonData(context,FILE_CONST, data)
        }

        fun addCategory(context: Context?, categoryName: String) {
            val data = readJson(context, FILE_CONST)
            data?.put(categoryName, KEYWORD_CONST)
            writeJsonData(context, FILE_CONST, data)
        }

        fun deleteCategory(context: Context?, categoryName: String) {
            val data = readJson(context, FILE_CONST)
            data?.remove(categoryName)
            writeJsonData(context,FILE_CONST, data)
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

        fun deleteStatsFile(context: Context?){
            File(context?.filesDir?.absolutePath, STATS_CONST).delete()
        }

        fun writeFiles(context: Context?) {
            //TODO convert was auch immer wir für ne liste benutzen zu Map ??
            val file = File(context?.filesDir?.absolutePath, FILE_CONST)
            if (!file.exists()) {
                val categoryMap: MutableMap<String, MutableList<String>> = mutableMapOf()
                categoryMap["Nature"] = mutableListOf("water", "tree", "dirt", "flower", "bird")

                val jsonObject = JSONObject(categoryMap as Map<String, MutableList<String>>).toString()

                try {
                    context?.openFileOutput(FILE_CONST, Context.MODE_PRIVATE).use { output ->
                        output?.write(jsonObject.toByteArray())
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            val statsFile = File(context?.filesDir?.absolutePath, STATS_CONST)
            if (!statsFile.exists()) {
                try {
                    val js = JSONObject().toString()
                    context?.openFileOutput(STATS_CONST, Context.MODE_PRIVATE).use { output ->
                        output?.write(js.toByteArray())
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }

        //region HelpMethods
        fun getCategoryLength(context: Context?, categoryName: String?): Int? {
            getSavedFile(context)?.forEach {
                if (it.first == categoryName)
                    return it.second.size
            }
            return null
        }

        private fun writeJsonData(context: Context?,file: String, data: JSONObject?) {
            try {
                context?.openFileOutput(file, Context.MODE_PRIVATE).use { output ->
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