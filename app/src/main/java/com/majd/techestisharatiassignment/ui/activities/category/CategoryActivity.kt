package com.majd.techestisharatiassignment.ui.activities.category

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.majd.techestisharatiassignment.R
import com.majd.techestisharatiassignment.adapters.BooksAdapter
import com.majd.techestisharatiassignment.adapters.CategoriesAdapter
import com.majd.techestisharatiassignment.models.BookModel
import com.majd.techestisharatiassignment.server.BignosevnServer
import com.majd.techestisharatiassignment.ui.activities.book.BookActivity
import kotlinx.android.synthetic.main.activity_book.*
import kotlinx.android.synthetic.main.activity_category.*
import kotlinx.android.synthetic.main.fragment_home.*

class CategoryActivity : AppCompatActivity(), CategoryView {
    lateinit var presenter: CategoryPresenter
    var adapter: BooksAdapter? = null

    var page = 0
    var size = 20

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        // Toolbar pressing back action
        _toolbar.setNavigationOnClickListener {
            finish()
        }
        // Initiating presenter
        presenter = CategoryPresenter(this, BignosevnServer.estisharatiService)
        // Init actions listeners
        initListeners()
        // Requesting first page
        presenter.books(page, size)
    }

    // Init actions listeners
    private fun initListeners() {
        recycler.addOnScrollListener(object: RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (!recyclerView.canScrollVertically(1)) {
                    page += 1
                    presenter.books(page, size)
                }
            }
        })
    }

    // Initiate books RecyclerView
    private fun initBooksRecyclerView(books: List<BookModel>) {
        adapter = BooksAdapter(books = books.toMutableList()).apply {
            setOnBookClicked {
                var intent = Intent(this@CategoryActivity, BookActivity::class.java).apply {
                    putExtra("book", it)
                }
                startActivity(intent)
            }
        }
        recycler.layoutManager = GridLayoutManager(this, 2)
        recycler.adapter = adapter
    }

    // Callback will be called when books request is being sent
    override fun onLoadingBooks() {

    }

    // Callback will be called when books response gets received with success
    override fun onBooksLoaded(books: List<BookModel>) {
        // Adapter has not been created yet, which means its the first books page
        if(adapter == null) {
            initBooksRecyclerView(books)
            loading.visibility = View.GONE
        }
        // Books has been loaded before, soo add more books to current existing list
        else {
            adapter?.add(books)
        }
    }

    // Callback will be called when books request fail
    override fun onBooksFailed() {

    }

    override fun onDestroy() {
        super.onDestroy()
        // Stop all coroutines
        presenter.cancel()
    }
}