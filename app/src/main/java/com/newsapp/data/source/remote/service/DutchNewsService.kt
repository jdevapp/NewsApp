package com.newsapp.data.source.remote.service

import com.newsapp.data.source.remote.entity.TopHeadlineResponse
import retrofit2.http.GET
import retrofit2.http.Query

const val COUNTRY = "nl"
const val APIKEY = "143df5e39fd844bfbdf26ed16c67df44"
const val TOP_HEADLINES_URL = "top-headlines/?apiKey=$APIKEY"

interface DutchNewsService {
    @GET(TOP_HEADLINES_URL)
    suspend fun getTopHeadlines(@Query("country") lang: String = COUNTRY): TopHeadlineResponse
}