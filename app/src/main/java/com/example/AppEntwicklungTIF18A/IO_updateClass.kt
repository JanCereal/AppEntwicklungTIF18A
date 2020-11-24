package com.example.AppEntwicklungTIF18A

class IO_updateClass {

    var categoryCollection = ArrayList<Category>()

    constructor() {
        //TODO idk?
    }

    fun updateCategory(changedCategory: ArrayList<String>) {
        //TODO update Strings in der bearbeiteten Kategorie und speicher die Änderung in File?
    }

    fun updateSavedFile(toSave: ArrayList<Category>) {
        //TODO update die hinterlegte File?
    }

    fun getSavedFile(): ArrayList<Category> {
        //TODO get Collection von der hinterlegten File?
        return categoryCollection
    }
    /*
private fun writeCategoryJson() {
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

private fun readCategoryJson() {
    context?.openFileInput("categoryData.json").use { stream ->
        val text = stream?.bufferedReader().use {
            val data = JSONObject(it?.readText())
            // TODO Update categoryMap with json in external Storage
            //Toast.makeText(requireContext().applicationContext,it?.readText(),Toast.LENGTH_LONG).show()
        }
    }
}
*/
}