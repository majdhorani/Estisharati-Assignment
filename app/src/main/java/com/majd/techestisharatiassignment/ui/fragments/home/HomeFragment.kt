package com.majd.techestisharatiassignment.ui.fragments.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.majd.techestisharatiassignment.R
import com.majd.techestisharatiassignment.adapters.CategoriesAdapter
import com.majd.techestisharatiassignment.adapters.HomeAdapter
import com.majd.techestisharatiassignment.models.CategoryModel
import com.majd.techestisharatiassignment.server.GithubServer
import com.majd.techestisharatiassignment.ui.activities.book.BookActivity
import com.majd.techestisharatiassignment.ui.activities.category.CategoryActivity
import kotlinx.android.synthetic.main.fragment_home.*

class HomeFragment : Fragment(), HomeView {
    // Presenters
    lateinit var presenter: HomePresenter
    // RecyclerView adapters
    lateinit var categoriesAdapter: CategoriesAdapter
    lateinit var homeAdapter: HomeAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Initiate presenters
        initPresenters()
        // Initiate actions listeners
        initListeners()
        // Fetch categories
        presenter.categories()
        // Fetch home
        presenter.home()
    }

    // Initiate presenters
    private fun initPresenters() {
        presenter = HomePresenter(this, GithubServer.estisharatiService)
    }

    // Initiate listeners
    private fun initListeners() {
        categoriesHeader.setOnClickListener {
            startActivity(Intent(activity, CategoryActivity::class.java))
        }
    }

    override fun onLoadingCategories() {
        loadingCategories.visibility = View.VISIBLE
    }

    override fun onLoadingHome() {
        loadingHome.visibility = View.VISIBLE
    }

    private fun initCategoriesRecyclerView(categories: List<CategoryModel>) {
        // Creating adapter
        categoriesAdapter = CategoriesAdapter(activity, categories)
        // Setting layout manager to be grid layout with 4 columns
        categoriesRecycler.layoutManager = GridLayoutManager(activity, 4)
        // Attaching adapter to recyclerView
        categoriesRecycler.adapter = categoriesAdapter
    }

    private fun initHomeRecyclerView(home: List<CategoryModel>) {
        homeAdapter = HomeAdapter(home)
        homeRecycler.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        homeRecycler.adapter = homeAdapter
        homeAdapter.setOnHeaderClicked {
            startActivity(Intent(activity, CategoryActivity::class.java))
        }
        homeAdapter.setOnBookClicked {
            var intent = Intent(activity, BookActivity::class.java).apply {
                putExtra("book", it)
            }
            startActivity(intent)
        }
    }

    // Callback will be called when categories response gets received with success
    override fun onCategoriesLoaded(categories: List<CategoryModel>) {
        initCategoriesRecyclerView(categories)
        loadingCategories.visibility = View.GONE
    }

    // Callback will be called when home response gets received with success
    override fun onHomeLoaded(home: List<CategoryModel>) {
        initHomeRecyclerView(home)
        loadingHome.visibility = View.GONE
    }

    // Callback will be called when categories request fail
    override fun onCategoriesFailed() {
        // Show refresh button
    }

    // Callback will be called when home request fail
    override fun onHomeFailed() {
        // Show refresh button
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Stop all coroutines jobs
        presenter.cancel()
    }
}