package com.task.data.model


import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import com.task.data.model.NewsItem

@JsonClass(generateAdapter = true)
data class NewsModel(
    @Json(name = "copyright")
    val copyright: String = "",
    @Json(name = "last_updated")
    val lastUpdated: String = "",
    @Json(name = "num_results")
    val numResults: Int = 0,
    @Json(name = "results")
    val newsItems: List<NewsItem> = listOf(),
    @Json(name = "section")
    val section: String = "",
    @Json(name = "status")
    val status: String = ""
)