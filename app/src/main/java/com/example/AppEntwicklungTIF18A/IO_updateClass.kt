package com.example.AppEntwicklungTIF18A

class IO_updateClass {

    var categoryCollection = ArrayList<Category>()
    constructor() {
        //TODO idk?
    }

    fun updateCategory( changedCategory : ArrayList<String>) {
        //TODO update Strings in der bearbeiteten Kategorie und speicher die Änderung in File?
    }

    fun updateSavedFile( toSave : ArrayList<Category>) {
        //TODO update die hinterlegte File?
    }

    fun getSavedFile(): ArrayList<Category> {
        //TODO get Collection von der hinterlegten File?
        return categoryCollection
    }
}