package com.task.usecase

import androidx.lifecycle.MutableLiveData
import com.task.data.Resource
import com.task.data.model.NewsItem
import com.task.data.model.NewsModel

/**
 * Created by AhmedEltaher
 */

interface UseCase {
    fun getNews()
    fun searchByTitle(keyWord: String): NewsItem?
    val newsLiveData: MutableLiveData<Resource<NewsModel>>
}
