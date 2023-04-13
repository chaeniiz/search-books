package com.example.booksearch.home

import com.example.entity.BookDetail

sealed class HomeState {
    object Loading : HomeState()
    data class Contents(val bookDetails: List<BookDetail>) : HomeState()
    object Error : HomeState()
}
