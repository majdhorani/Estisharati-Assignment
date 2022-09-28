package com.majd.techestisharatiassignment.server

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object BignosevnServer {
    var estisharatiService : EstisharatiService
    private var retrofit: Retrofit

    init {
        // Retrofit
        retrofit = Retrofit.Builder()
            .baseUrl(Urls.BIGNOSEVN_BASE)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // Services
        estisharatiService = retrofit.create(EstisharatiService::class.java)
    }

}