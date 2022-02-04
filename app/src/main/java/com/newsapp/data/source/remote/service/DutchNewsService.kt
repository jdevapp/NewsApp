package com.newsapp.data.source.remote.service

import com.newsapp.data.source.remote.entity.TopHeadlineResponse
import retrofit2.http.GET

interface DutchNewsService {
    @GET
    suspend fun getTopHeadlines(): List<TopHeadlineResponse>

}