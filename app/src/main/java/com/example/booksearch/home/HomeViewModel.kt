package com.example.booksearch.home

import android.util.Log
import com.example.booksearch.base.BaseViewModel
import com.example.domain.repositories.BookRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: BookRepository
) : BaseViewModel() {

    fun initialize() = launch {
        val books = repository.getBooks("android")
        Log.d("chaeniiz:books", books.toString())
        val bookDetails = books.forEach { book ->
            repository.getBookDetail(book.isbn13)
        }
        Log.d("chaeniiz:bookdetails", bookDetails.toString())
    }
}
