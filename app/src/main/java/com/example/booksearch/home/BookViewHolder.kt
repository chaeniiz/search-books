package com.example.booksearch.home

import android.net.Uri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.booksearch.databinding.ItemBookBinding
import com.example.entity.BookDetail

class BookViewHolder(private val binding: ItemBookBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(book: BookDetail) {
        with(binding) {
            Glide.with(root.context).load(book.imageUrl).into(bookImageView)
            bookImageView.setImageURI(Uri.parse(book.imageUrl))
            titleTextView.text = book.title
            authorTextView.text = book.authors
            publisherTextView.text = book.publisher
            priceTextView.text = book.price
        }
    }
}
