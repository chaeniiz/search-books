package com.example.entity

data class BookDetail(
    val error: String,
    val title: String,
    val subtitle: String,
    val authors: String,
    val publisher: String,
    val isbn10: String,
    val isbn13: String,
    val pages: Int,
    val year: Int,
    val rating: Int,
    val description: String,
    val price: String,
    val imageUrl: String,
    val url: String,
    val pdf: List<String>
)
