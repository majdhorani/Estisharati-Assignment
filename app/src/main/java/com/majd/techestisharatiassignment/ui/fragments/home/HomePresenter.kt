package com.majd.techestisharatiassignment.ui.fragments.home

import com.majd.techestisharatiassignment.server.EstisharatiService
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class HomePresenter constructor(val view: HomeView, val estisharatiService: EstisharatiService) : CoroutineScope {
    private val job = SupervisorJob()

    // Fetch categories data
    fun categories() {
        // Indicate as loading
        view.onLoadingCategories()
        launch(Dispatchers.Main) {
            try {
                // Fetching home
                var response = estisharatiService.categories()
                // Home loaded
                withContext(Dispatchers.Main) {
                    view.onCategoriesLoaded(response.data)
                }
            }
            catch (exception: Exception) {
                // Home failed
                exception.printStackTrace()
                withContext(Dispatchers.Main) {
                    view.onCategoriesFailed()
                }
            }
        }
    }

    // Fetch home data
    fun home() {
        // Indicate as loading
        view.onLoadingHome()
        launch(Dispatchers.Main) {
            try {
                // Fetching home
                var response = estisharatiService.home()
                // Home loaded
                withContext(Dispatchers.Main) {
                    view.onHomeLoaded(response.data)
                }
            }
            catch (exception: Exception) {
                // Home failed
                exception.printStackTrace()
                withContext(Dispatchers.Main) {
                    view.onHomeFailed()
                }
            }
        }
    }

    fun cancel() {
        job.cancel()
    }

    override val coroutineContext: CoroutineContext = job
}