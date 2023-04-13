package com.example.data.network.repositories

import com.example.data.network.api.BookApi
import com.example.data.network.response.toEntity
import com.example.domain.repositories.BookRepository
import com.example.entity.Book
import com.example.entity.BookDetail
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DefaultBookRepository @Inject constructor(
    private val bookApi: BookApi
) : BookRepository {
    override suspend fun getBooks(query: String): List<Book> =
        bookApi.getBooks(query).books.map { it.toEntity() }

    override suspend fun getBookDetail(isbn13: String): BookDetail =
        bookApi.getBookDetail(isbn13).let {
            BookDetail(
                it.error,
                it.title,
                it.subtitle,
                it.authors,
                it.publisher,
                it.isbn10,
                it.isbn13,
                it.pages,
                it.year,
                it.rating,
                it.description,
                it.price,
                it.imageUrl,
                it.url,
                it.pdf
            )
        }
}
