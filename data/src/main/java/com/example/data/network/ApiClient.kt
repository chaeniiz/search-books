package com.example.data.network

import com.example.data.network.api.BookApi
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ApiClient @Inject constructor() {

    companion object {
        const val CONNECT_TIMEOUT = "connect_timeout"
        const val READ_TIMEOUT = "read_timeout"
        const val WRITE_TIMEOUT = "write_timeout"
    }

    private val gson by lazy {
        GsonBuilder()
            .setLenient()
            .create()
    }

    private val adapter: Retrofit by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.itbook.store/1.0/")
            .client(buildOkHttpClient().build())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

    private fun buildOkHttpClient(
        timeout: Long = 20
    ): OkHttpClient.Builder {
        return OkHttpClient.Builder()
            .connectTimeout(timeout, TimeUnit.SECONDS)
            .readTimeout(timeout, TimeUnit.SECONDS)
            .writeTimeout(timeout, TimeUnit.SECONDS)
            .addInterceptor(createTimeOutInterceptor())
    }

    private fun createTimeOutInterceptor() = Interceptor { chain ->
        val request = chain.request()
        val requestBuilder = request.newBuilder()

        var connectTimeout = chain.connectTimeoutMillis()
        var readTimeout = chain.readTimeoutMillis()
        var writeTimeout = chain.writeTimeoutMillis()

        request.header(CONNECT_TIMEOUT)?.let { connectTimeout = it.toInt() }
        request.header(READ_TIMEOUT)?.let { readTimeout = it.toInt() }
        request.header(WRITE_TIMEOUT)?.let { writeTimeout = it.toInt() }

        chain
            .withConnectTimeout(connectTimeout, TimeUnit.SECONDS)
            .withReadTimeout(readTimeout, TimeUnit.SECONDS)
            .withWriteTimeout(writeTimeout, TimeUnit.SECONDS)
            .proceed(requestBuilder.build())
    }

    fun provideBookApi(): BookApi =
        adapter.create(BookApi::class.java)
}
