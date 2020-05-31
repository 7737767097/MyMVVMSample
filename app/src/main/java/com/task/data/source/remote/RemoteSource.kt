package com.task.data.source.remote

import com.task.data.Resource
import com.task.data.model.NewsModel

internal interface RemoteSource {
    suspend fun requestNews(): Resource<NewsModel>
}