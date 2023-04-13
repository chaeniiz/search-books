package com.example.booksearch

import androidx.multidex.MultiDexApplication
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Singleton

@Singleton
open class BookSearchApplication : MultiDexApplication()

@HiltAndroidApp
class BookSearch : BookSearchApplication()
