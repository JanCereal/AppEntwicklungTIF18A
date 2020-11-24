package com.example.AppEntwicklungTIF18A

import android.content.Context
import org.json.JSONObject
import java.io.File

class IO_updateClass {

    var categoryCollection = ArrayList<Category>()

    constructor() {
    }

    fun updateCategory(context: Context, changedCategory: Category) {
        //TODO update Strings in der bearbeiteten Kategorie und speicher die Änderung in File?
    }

    fun updateSavedFile(context: Context, toSave: ArrayList<Category>) {
        //TODO update die hinterlegte File?
    }

    fun getSavedFile(context: Context): ArrayList<Category> {
        val text = readCategoryJson(context)
        //TODO parse text in Collection

        return categoryCollection
    }

private fun writeCategoryJson(context:Context, category:Category) {
    val file = File(context?.filesDir, "categoryData")

    var jsonString = JSONObject(category._categoryMembers as Map<String, MutableList<String>>).toString()

    try {
        val fileName = "categoryData.json"
        context?.openFileOutput(fileName, Context.MODE_PRIVATE).use { output ->
            output?.write(jsonString.toByteArray())
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}

private fun readCategoryJson(context:Context) {
    context?.openFileInput("categoryData.json").use { stream ->
        val text = stream?.bufferedReader().use {
            val data = JSONObject(it?.readText())
            // TODO Update categoryMap with json in external Storage
            //Toast.makeText(requireContext().applicationContext,it?.readText(),Toast.LENGTH_LONG).show()
        }
    }
}

}