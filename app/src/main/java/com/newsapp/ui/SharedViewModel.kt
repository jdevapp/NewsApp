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

    private val _firstArticle = MutableStateFlow<Article?>(null)
    val firstArticle: StateFlow<Article?> = _firstArticle

    private val _secondArticle = MutableStateFlow<Article?>(null)
    val secondArticle: StateFlow<Article?> = _secondArticle

    private val _thirdArticle = MutableStateFlow<Article?>(null)
    val thirdArticle: StateFlow<Article?> = _thirdArticle

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
                    _firstArticle.value  = articles.first()
                    _secondArticle.value  = articles[1]
                    _thirdArticle.value  = articles[2]
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

