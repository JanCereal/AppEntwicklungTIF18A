package com.example.AppEntwicklungTIF18A

class Category(categoryName: String, categoryMembers: MutableList<String>) {
    val _categoryName= categoryName
    val _categoryMembers = categoryMembers

    fun getCategoryName(): String {
        return _categoryName
    }

    fun getCategoryList() : MutableList<String> {
        return _categoryMembers
    }
}

