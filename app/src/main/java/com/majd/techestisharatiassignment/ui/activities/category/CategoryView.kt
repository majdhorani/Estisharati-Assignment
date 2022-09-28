package com.majd.techestisharatiassignment.ui.activities.category

import com.majd.techestisharatiassignment.models.BookModel
import com.majd.techestisharatiassignment.models.CategoryModel

interface CategoryView {
    fun onLoadingBooks()
    fun onBooksLoaded(books: List<BookModel>)
    fun onBooksFailed()
}