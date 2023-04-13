package com.example.booksearch.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob

abstract class BaseViewModel : ViewModel(), CoroutineScope {
    private val disposableJob = SupervisorJob()
    override val coroutineContext = Dispatchers.Main.immediate + disposableJob

    public override fun onCleared() {
        disposableJob.cancel()
        super.onCleared()
    }
}
