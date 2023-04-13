package com.example.data.network.response

import com.google.gson.annotations.SerializedName

data class GetBooksResponse(
    @SerializedName("total")
    val total: Int,

    @SerializedName("page")
    val page: Int,

    @SerializedName("books")
    val books: List<BookResponse>
)
