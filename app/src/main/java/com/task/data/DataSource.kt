package com.task.data

import com.task.data.model.NewsModel

interface DataSource {

    suspend fun requestNews(): Resource<NewsModel>

    suspend fun getOverView(): String

//    suspend fun getAllUser(): Observable<List<User?>?>?
}