package com.task.data

import com.task.data.model.NewsModel
import com.task.data.source.db.User
import io.reactivex.Observable

interface DataSource {

    suspend fun requestNews(): Resource<NewsModel>

    suspend fun getOverView(): String

    suspend fun getAllUser(): Observable<List<User?>?>?
}