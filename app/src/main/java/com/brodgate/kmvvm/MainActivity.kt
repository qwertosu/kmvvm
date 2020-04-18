package com.brodgate.kmvvm

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel


class MainActivity : AppCompatActivity() {

    private val movieViewModel: MovieViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val adapter = MovieAdapter()
        recycler.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            this.adapter = adapter
        }
        movieViewModel.uiState.observe(this, Observer {
            val dataState = it ?: return@Observer
            if (dataState.movies != null && dataState.movies.isNotEmpty()) {
                dataState.movies.let {
                    println(it)
                    adapter.submitList(it)
                }
            }
        })
    }
}
