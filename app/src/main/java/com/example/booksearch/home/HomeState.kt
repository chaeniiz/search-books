package com.example.booksearch.home

import com.example.entity.Book

sealed class HomeState {
    object Loading : HomeState()
    data class Contents(val books: List<Book>)
}
