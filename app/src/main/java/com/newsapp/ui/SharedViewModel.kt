package com.newsapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.newsapp.domain.model.Article
import com.newsapp.domain.repository.ArticlesRepository
import com.newsapp.result.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SharedViewModel @Inject constructor(
    private val articlesRepository: ArticlesRepository
) : ViewModel() {

    private val _articles = MutableStateFlow<List<Article>>(emptyList())
    val articles: StateFlow<List<Article>> = _articles

    init {
        fetchArticles()
    }

    private fun fetchArticles() {
        viewModelScope.launch {
            val result = articlesRepository.getArticles()
            when (result) {
                is Result.Success -> {
                    val articles =  result.data
                    _articles.value = articles
                }
                is Result.Loading -> {}
                is Result.Error -> {}
            }
        }
    }

    fun refresh(){
        fetchArticles()
    }

}

