package com.example.AppEntwicklungTIF18A

class Category(imageResource: Int, categoryName: String) {
    val image= imageResource
    val txtCategoryName= categoryName

    fun getCategoryImage(): Int {
        return image
    }

    fun getCategoryName(): String {
        return txtCategoryName
    }
}

