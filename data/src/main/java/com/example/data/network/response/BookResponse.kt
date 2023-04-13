package com.example.data.network.response

import com.example.entity.Book
import com.google.gson.annotations.SerializedName

data class BookResponse(
    @SerializedName("title")
    val title: String,

    @SerializedName("subtitle")
    val subtitle: String,

    @SerializedName("isbn13")
    val isbn13: String,

    @SerializedName("price")
    val price: String,

    @SerializedName("image")
    val imageUrl: String,

    @SerializedName("url")
    val url: String
)

fun BookResponse.toEntity(): Book = Book(
    title,
    subtitle,
    isbn13,
    price,
    imageUrl,
    url
)
