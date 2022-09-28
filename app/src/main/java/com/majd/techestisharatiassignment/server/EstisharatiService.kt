package com.majd.techestisharatiassignment.server

import com.majd.techestisharatiassignment.models.BookModel
import com.majd.techestisharatiassignment.models.CategoryModel
import com.majd.techestisharatiassignment.server.responses.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface EstisharatiService {
    @GET(Urls.HOME)
    suspend fun home() : Response<List<CategoryModel>>

    @GET(Urls.CATEGORIES)
    suspend fun categories() : Response<List<CategoryModel>>

    @GET(Urls.BOOKS)
    suspend fun books(@Query("limit") limit: Number, @Query("start") start: Number) : List<BookModel>
}