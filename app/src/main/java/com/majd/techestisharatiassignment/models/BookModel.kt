package com.majd.techestisharatiassignment.models

import java.io.Serializable

data class BookModel(
    val id: String,
    val title: String,
    val author: String,
    val description: String,
    val url: String,
    val img: String,
    val genres: String,
    val lastest: String,
    val top: String,
    val japan: String
    ) : Serializable