package com.majd.techestisharatiassignment.ui.activities.book

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.majd.techestisharatiassignment.R
import com.majd.techestisharatiassignment.models.BookModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_book.*

class BookActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_book)

        // Toolbar pressing back action
        toolbar.setNavigationOnClickListener {
            finish()
        }
        // Fetching sent book details from previous activity
        var book : BookModel = intent.getSerializableExtra("book") as BookModel
        // Binding book details with ui
        bind(book)
    }

    // Binding book details model with ui
    private fun bind(book: BookModel) {
        // Setting book title
        with(book.title) {
            toolbar.title = this;
            name.text = this
        }

        // Setting book image
        book.img?.apply {
            Picasso.get().load(this).fit().centerCrop().into(image)
        }

        // Setting author name
        book.author?.apply {
            author.text = this
        }

        // Setting description
        book.description?.apply {
            description.text = this
        }

        // Setting genres
        book.genres?.apply {
            genre.text = this
        }
    }
}