package com.majd.techestisharatiassignment.ui.fragments.home

import com.majd.techestisharatiassignment.models.CategoryModel

interface HomeView {
    fun onLoadingCategories()
    fun onCategoriesLoaded(categories: List<CategoryModel>)
    fun onCategoriesFailed()
    fun onLoadingHome();
    fun onHomeLoaded(home: List<CategoryModel>)
    fun onHomeFailed()
}