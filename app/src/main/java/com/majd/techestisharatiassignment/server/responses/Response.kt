package com.majd.techestisharatiassignment.server.responses

// Common generic server response
data class Response<T>(
    val data: T,
    val status: Boolean,
    val errorCode: Number
)
