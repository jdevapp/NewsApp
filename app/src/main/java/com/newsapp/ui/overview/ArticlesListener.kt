package com.newsapp.ui.overview

import com.newsapp.domain.model.Article

interface ArticlesListener {
    fun onArticleClicked(article: Article)
}
