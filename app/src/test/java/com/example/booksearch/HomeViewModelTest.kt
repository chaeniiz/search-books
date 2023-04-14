package com.example.booksearch

import com.example.booksearch.home.HomeState
import com.example.booksearch.home.HomeViewModel
import com.example.domain.repositories.BookRepository
import com.example.entity.Book
import com.example.entity.BookDetail
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.HttpException
import retrofit2.Response

@ExperimentalCoroutinesApi
class HomeViewModelTest {

    private lateinit var viewModel: HomeViewModel

    @Mock
    lateinit var bookRepository: BookRepository

    @Before
    fun setup() {
        MockitoAnnotations.openMocks(this)
        Dispatchers.setMain(UnconfinedTestDispatcher())
        viewModel = HomeViewModel(bookRepository)
    }

    @Test
    fun `앱 진입 시 android로 검색되는 도서 목록을 반환받는다`() = runTest {
        /** given */
        val books = listOf(
            Book("title", "subtitle", "1234567890123", "$13.0", "", "")
        )
        val bookDetail = BookDetail(
            "error",
            "title",
            "subtitle",
            "authors",
            "publisher",
            "1234567890",
            "1234567890123",
            1,
            1,
            1,
            "description",
            "$13.0",
            "",
            "",
            null
        )
        Mockito.`when`(bookRepository.getBooks("android")).thenReturn(books)
        Mockito.`when`(bookRepository.getBookDetail("1234567890123")).thenReturn(bookDetail)

        /** when */
        viewModel.initialize()

        /** then */
        assertEquals(viewModel.state.value, HomeState.Contents(listOf(bookDetail)))
    }

    @Test
    fun `getBooks() 도중 에러 발생 시 다이얼로그를 노출한다`() = runTest {
        /** given */
        Mockito.`when`(bookRepository.getBooks("android")).thenThrow(httpException)

        /** when */
        viewModel.initialize()

        /** then */
        assertEquals(viewModel.state.value, HomeState.Error)
    }

    @Test
    fun `getBookDetail() 도중 에러 발생 시 다이얼로그를 노출한다`() = runTest {
        /** given */
        val books = listOf(
            Book("title", "subtitle", "1234567890123", "$13.0", "", "")
        )
        Mockito.`when`(bookRepository.getBooks("android")).thenReturn(books)
        Mockito.`when`(bookRepository.getBookDetail("1234567890123")).thenThrow(httpException)

        /** when */
        viewModel.initialize()

        /** then */
        assertEquals(viewModel.state.value, HomeState.Error)
    }

    companion object {
        val httpException = HttpException(
            Response.error<String>(
            404,
                "Error".toResponseBody("Error".toMediaTypeOrNull())
            )
        )
    }
}
