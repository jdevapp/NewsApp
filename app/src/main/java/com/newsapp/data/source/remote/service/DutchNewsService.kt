package com.newsapp.data.source.remote.service

import com.newsapp.data.source.remote.entity.TopHeadlineResponse
import retrofit2.http.GET
import retrofit2.http.Query

const val COUNTRY = "nl"
const val APIKEY = "your API_KEY"
const val TOP_HEADLINES_URL = "top-headlines/?apiKey=$APIKEY"

interface DutchNewsService {
    @GET(TOP_HEADLINES_URL)
    suspend fun getTopHeadlines(@Query("country") lang: String = COUNTRY): TopHeadlineResponse
}