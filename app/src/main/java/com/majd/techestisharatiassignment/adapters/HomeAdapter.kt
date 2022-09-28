package com.majd.techestisharatiassignment.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.majd.techestisharatiassignment.R
import com.majd.techestisharatiassignment.models.BookModel
import com.majd.techestisharatiassignment.models.CategoryModel

class HomeAdapter(
    val categories: List<CategoryModel>)
    : RecyclerView.Adapter<HomeAdapter.Holder>() {

    var onHeaderClickedCallback: (() -> Unit)? = null
    var onBookClickedCallback : ((BookModel) -> Unit)? = null

    fun setOnBookClicked(callback: (BookModel) -> Unit) {
        onBookClickedCallback = callback
    }

    fun setOnHeaderClicked(callback: () -> Unit) {
        onHeaderClickedCallback = callback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view: View = LayoutInflater.from(parent.context).inflate(
            R.layout.home_item,
            parent,
            false
        )
        return Holder(view)
    }


    override fun onBindViewHolder(holder: Holder, position: Int) {
        var category = categories[position]
        holder.bindData(item = category)

        onHeaderClickedCallback?.apply {
            holder.setOnHeaderClicked(this)
        }

        onBookClickedCallback?.apply {
            holder.setOnBookClicked(this)
        }
    }

    override fun getItemCount(): Int = categories.size


    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var title: TextView
        var recycler: RecyclerView
        var header: RelativeLayout

        init {
            title = itemView.findViewById(R.id.name)
            recycler = itemView.findViewById(R.id.recycler)
            header = itemView.findViewById(R.id.header)
        }

        fun bindData(item: CategoryModel) {
            // Setting title
            if (item.name != null) {
                title.text = item.name
            }

            // Setting books
            if(!item.books.isNullOrEmpty()) {
                recycler?.apply {
                    layoutManager = LinearLayoutManager(itemView.context, LinearLayoutManager.HORIZONTAL, false)
                    adapter = HomeBooksAdapter(books = item.books)
                }
            }
        }

        fun setOnHeaderClicked(callback: () -> Unit) {
            header.setOnClickListener { callback() }
        }

        fun setOnBookClicked(callback: (BookModel) -> Unit) {
            (recycler.adapter as HomeBooksAdapter).setOnBookClicked(callback)
        }
    }
}