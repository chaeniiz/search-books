package com.example.data.network.response

import com.google.gson.annotations.SerializedName

data class GetBookDetailResponse(
    @SerializedName("error")
    val error: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("subtitle")
    val subtitle: String,

    @SerializedName("authors")
    val authors: String,

    @SerializedName("publisher")
    val publisher: String,

    @SerializedName("isbn10")
    val isbn10: String,

    @SerializedName("isbn13")
    val isbn13: String,

    @SerializedName("pages")
    val pages: Int,

    @SerializedName("year")
    val year: Int,

    @SerializedName("rating")
    val rating: Int,

    @SerializedName("desc")
    val description: String,

    @SerializedName("price")
    val price: String,

    @SerializedName("image")
    val imageUrl: String,

    @SerializedName("url")
    val url: String,

    @SerializedName("pdf")
    val pdf: List<String>?
)
