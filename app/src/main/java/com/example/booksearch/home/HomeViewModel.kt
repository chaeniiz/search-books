package com.example.booksearch.home

import com.example.booksearch.base.BaseViewModel
import com.example.domain.repositories.BookRepository
import com.example.entity.BookDetail
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val repository: BookRepository
) : BaseViewModel() {

    val state = MutableStateFlow<HomeState>(HomeState.Loading)

    fun initialize() = launch {
        state.emit(HomeState.Loading)
        try {
            val books = repository.getBooks("android")
            val bookDetails: MutableList<BookDetail> = mutableListOf()
            books.forEach { book ->
                bookDetails.add(repository.getBookDetail(book.isbn13))
            }
            state.emit(HomeState.Contents(bookDetails))
        } catch (e: Throwable) {
            state.emit(HomeState.Error)
        }
    }
}
