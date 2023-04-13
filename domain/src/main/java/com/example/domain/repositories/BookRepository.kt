package com.example.domain.repositories

import com.example.entity.Book
import com.example.entity.BookDetail

interface BookRepository {
    suspend fun getBooks(query: String): List<Book>
    suspend fun getBookDetail(isbn13: String): BookDetail
}
