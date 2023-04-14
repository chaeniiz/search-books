package com.example.booksearch.home

import android.graphics.Rect
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.booksearch.R
import com.example.booksearch.databinding.ActivityHomeBinding
import com.example.entity.BookDetail
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        observeState()
        viewModel.initialize()
    }

    private fun observeState() {
        lifecycleScope.launch {
            viewModel.state.collect { state ->
                when (state) {
                    HomeState.Error -> AlertDialog.Builder(this@HomeActivity).apply {
                        setPositiveButton(R.string.retry) { _, _ -> viewModel.initialize() }
                        setNegativeButton(R.string.exit) { _, _ -> this@HomeActivity.finish() }
                        setMessage(R.string.error_msg)
                    }.create().show()
                    HomeState.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.recyclerView.visibility = View.GONE
                    }
                    is HomeState.Contents -> {
                        binding.progressBar.visibility = View.GONE
                        setAdapter(state.bookDetails)
                        binding.recyclerView.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    private fun setAdapter(bookDetails: List<BookDetail>) {
        with(binding.recyclerView) {
            adapter = HomeRecyclerAdapter(bookDetails)
            layoutManager = LinearLayoutManager(context)
            setItemViewCacheSize(10)
            addItemDecoration(object : RecyclerView.ItemDecoration() {
                override fun getItemOffsets(
                    outRect: Rect,
                    view: View,
                    parent: RecyclerView,
                    state: RecyclerView.State
                ) {
                    outRect.bottom = 24
                }
            })
        }
    }
}
