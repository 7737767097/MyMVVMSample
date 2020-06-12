package com.task.usecase

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.task.data.DataSource
import com.task.data.Resource
import com.task.data.error.Error.Companion.NETWORK_ERROR
import com.task.data.model.NewsItem
import com.task.data.model.NewsModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext


/**
 * Created by AhmedEltaher
 */

class NewsUseCase @Inject
constructor(
    private val dataRepository: DataSource,
    override val coroutineContext: CoroutineContext
) : UseCase, CoroutineScope {

    private val newsMutableLiveData = MutableLiveData<Resource<NewsModel>>()

    override val newsLiveData: MutableLiveData<Resource<NewsModel>> = newsMutableLiveData

    /*Requesting news data from here */
    override fun getNews() {
        var serviceResponse: Resource<NewsModel>?
        newsMutableLiveData.postValue(Resource.Loading())
        launch {
            try {
                serviceResponse = dataRepository.requestNews()
                newsMutableLiveData.postValue(serviceResponse)
            } catch (e: Exception) {
                e.printStackTrace()
                newsMutableLiveData.postValue(Resource.DataError(NETWORK_ERROR))
            }
        }
    }

    /*Searching from list of news data*/
    override fun searchByTitle(keyWord: String): NewsItem? {
        val news = newsMutableLiveData.value?.data?.newsItems
        if (!news.isNullOrEmpty()) {
            for (newsItem in news) {
                if (newsItem.title.isNotEmpty() && newsItem.title.toLowerCase()
                        .contains(keyWord.toLowerCase())
                ) {
                    return newsItem
                }
            }
        }
        return null
    }
}
