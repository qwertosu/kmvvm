package com.brodgate.kmvvm

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.koin.core.KoinComponent
import org.koin.core.inject
import java.nio.charset.StandardCharsets.UTF_8

class MovieViewModel : ViewModel(), KoinComponent {

    private val _uiState = MutableLiveData<DataState>()
    val uiState: LiveData<DataState> get() = _uiState
    private val app: Application by inject()

    init {
        fetchMoviesData()
    }

    private fun fetchMoviesData() {
        viewModelScope.launch {
            getMoviesData()
        }
    }

    private suspend fun getMoviesData() = withContext(Dispatchers.IO) {
        try {
            val input = app.assets.open("movies.json")
            val size = input.available()
            val buffer = ByteArray(size)
            input.apply {
                read(buffer)
                close()
            }
            val json = String(buffer,UTF_8)
            val result = Gson().fromJson(json,Array<Movie>::class.java).toList()
            val data = DataState(result,null)
            withContext(Dispatchers.Main){
                _uiState.value = data
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}