package com.majd.techestisharatiassignment.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.majd.techestisharatiassignment.R
import com.majd.techestisharatiassignment.models.BookModel
import com.squareup.picasso.Picasso

class HomeBooksAdapter(
    val books: List<BookModel>)
    : RecyclerView.Adapter<HomeBooksAdapter.Holder>() {

    var onBookClickedCallback : ((BookModel) -> Unit)? = null

    fun setOnBookClicked(callback: (BookModel) -> Unit) {
        onBookClickedCallback = callback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.book_home_item,
            parent,
            false
        )
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        var book = books[position]
        holder.bindData(book)
        holder.itemView.setOnClickListener { onBookClickedCallback?.invoke(book) }
    }

    override fun getItemCount(): Int = books.size


    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var image: ImageView;
        var title: TextView
        var category: TextView

        init {
            title = itemView.findViewById(R.id.title)
            category = itemView.findViewById(R.id.category)
            image = itemView.findViewById(R.id.image)
        }

        fun bindData(item: BookModel) {
            if (item.img != null) {
                Picasso.get().load(item.img).fit().centerCrop().into(image)
            }
            if (item.title != null) {
                title.text = item.title
            }
            if (item.genres != null) {
                category.text = item.genres
            }
        }
    }
}