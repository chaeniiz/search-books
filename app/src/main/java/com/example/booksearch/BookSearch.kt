package com.example.booksearch

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Singleton

@Singleton
open class BookSearchApplication : Application()

@HiltAndroidApp
class BookSearch : BookSearchApplication()
