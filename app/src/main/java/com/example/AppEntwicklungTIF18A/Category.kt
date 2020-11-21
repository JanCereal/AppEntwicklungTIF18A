package com.example.AppEntwicklungTIF18A

class Category(imageResource: Int, categoryName: String, categoryMembers: MutableList<String>) {
    val _categoryImage= imageResource
    val _categoryName= categoryName
    val _categoryMembers = categoryMembers

    fun getCategoryImage(): Int {
        return _categoryImage
    }

    fun getCategoryName(): String {
        return _categoryName
    }

    fun getCategoryList() : MutableList<String> {
        return _categoryMembers
    }
}

