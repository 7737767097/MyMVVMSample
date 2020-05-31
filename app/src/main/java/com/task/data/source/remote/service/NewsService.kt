package com.task.data.source.remote.service

import com.task.data.model.NewsModel
import retrofit2.Response
import retrofit2.http.GET

interface NewsService {
    @GET("topstories/v2/home.json?api-key=4rfwOLzLTWd1a5xixcPjwddAhw3p0eiF")
    suspend fun fetchNews(): Response<NewsModel>
}