package com.majd.techestisharatiassignment.ui.activities.category

import android.util.Log
import com.majd.techestisharatiassignment.server.EstisharatiService
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class CategoryPresenter constructor(val view: CategoryView, val estisharatiService: EstisharatiService) : CoroutineScope {
    private val job = SupervisorJob()

    // Fetch categories data
    fun books(page: Number, size: Number = 30) {
        // Indicate as loading
        view.onLoadingBooks()

        launch(Dispatchers.Main) {
            try {
                // Fetching home
                var response = estisharatiService.books(limit = size, start = page)

                // Home loaded
                withContext(Dispatchers.Main) {
                    view.onBooksLoaded(response)
                }
            }
            catch (exception: Exception) {
                // Home failed
                exception.printStackTrace()
                withContext(Dispatchers.Main) {
                    view.onBooksFailed()
                }
            }
        }
   }

    fun cancel() {
        job.cancel()
    }

    override val coroutineContext: CoroutineContext = job
}