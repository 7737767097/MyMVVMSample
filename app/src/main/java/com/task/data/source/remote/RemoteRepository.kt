package com.task.data.source.remote

import com.task.App
import com.task.data.Resource
import com.task.data.error.Error
import com.task.data.model.NewsModel
import com.task.data.source.remote.service.NewsService
import com.task.utils.Network
import retrofit2.Response
import java.lang.Exception
import javax.inject.Inject

class RemoteRepository @Inject
constructor(private val serviceGenerator: ServiceGenerator) : RemoteSource {

    override suspend fun requestNews(): Resource<NewsModel> {
        val newService = serviceGenerator.createService(NewsService::class.java)
        return when (val response = processCall(newService::fetchNews)) {
            is NewsModel -> {
                Resource.Success(data = response)
            }
            else -> {
                Resource.DataError(errorCode = response as Int)
            }
        }
    }

    private suspend fun processCall(fetchNews: suspend () -> Response<*>): Any? {
        if (!Network.isConnected(App.mContext)) {
            return Error.NO_INTERNET_CONNECTION
        }
        return try {
            val response = fetchNews.invoke()
            if (response.isSuccessful) {
                response.body()
            } else response.code()
        } catch (e: Exception) {
            e.printStackTrace()
            Error.NETWORK_ERROR
        }
    }

}