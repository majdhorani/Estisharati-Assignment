package com.majd.techestisharatiassignment.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CategoryModel(
    @SerializedName("id")
    val id: Number,
    @SerializedName("name")
    val name: String,
    @SerializedName("image")
    val image: String,
    @SerializedName("books")
    val books: List<BookModel>?
    ) : Serializable