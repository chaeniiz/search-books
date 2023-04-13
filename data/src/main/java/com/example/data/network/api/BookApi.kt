package com.example.data.network.api

import com.example.data.network.ApiClient
import com.example.data.network.response.GetBookDetailResponse
import com.example.data.network.response.GetBooksResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Path
import retrofit2.http.Query

interface BookApi {

    @Headers(
        "${ApiClient.CONNECT_TIMEOUT}:10",
        "${ApiClient.READ_TIMEOUT}:30",
        "${ApiClient.WRITE_TIMEOUT}:10"
    )

    @GET("search/{query}")
    suspend fun getBooks(
        @Path("query") query: String
    ): GetBooksResponse

    @GET("books/{isbn13}")
    suspend fun getBookDetail(
        @Path("isbn13") isbn13: String
    ): GetBookDetailResponse
}
