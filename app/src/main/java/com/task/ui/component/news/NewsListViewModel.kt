package com.task.ui.component.news

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.task.data.Resource
import com.task.data.error.mapper.ErrorMapper
import com.task.data.model.NewsItem
import com.task.data.model.NewsModel
import com.task.ui.base.BaseViewModel
import com.task.usecase.NewsUseCase
import com.task.usecase.errors.ErrorManager
import com.task.utils.Event
import javax.inject.Inject

class NewsListViewModel @Inject constructor(private val newsDataUseCase: NewsUseCase) :
    BaseViewModel() {

    override val errorManager: ErrorManager
        get() = ErrorManager(ErrorMapper())

    /* *
    * Data -> LiveData, Exposed as LiveData, Locally in ViewModel as MutableLiveData
    * */
    var newsLiveData: MutableLiveData<Resource<NewsModel>> = newsDataUseCase.newsLiveData

    private val newsSearchFoundPrivate: MutableLiveData<NewsItem> = MutableLiveData()
    val newsSearchFound: LiveData<NewsItem> = newsSearchFoundPrivate

    private val noSearchFoundPrivate: MutableLiveData<Unit> = MutableLiveData()
    val noSearchFound: LiveData<Unit> = noSearchFoundPrivate

    /*
    * UI actions as event, user action  is single  one time event, shouldn't be multiple time consumption
    * */
    private val openNewsDetailsPrivate: MutableLiveData<Event<NewsItem>> = MutableLiveData()
    val openNewsDetails = openNewsDetailsPrivate

    /*
    * Error handing as UI
    * */
    private val showSnackBarPrivate = MutableLiveData<Event<Int>>()
    val showSnackBar: LiveData<Event<Int>> = showSnackBarPrivate

    private val showToastPrivate = MutableLiveData<Event<Any>>()
    val showToast: LiveData<Event<Any>> get() = showToastPrivate

    fun getNews() {
        newsDataUseCase.getNews()
    }

    fun openNewsDetails(newsItem: NewsItem) {
        openNewsDetailsPrivate.value = Event(newsItem)
    }

    fun showSnackBarMessage(@StringRes message: Int) {
        showSnackBarPrivate.value = Event(message)
    }

    fun showToastMessage(errorCode: Int) {
        val error = errorManager.getError(errorCode)
        showToastPrivate.value = Event(error)
    }

    fun onSearchClick(newsTitle: String) {
        if (newsTitle.isNotEmpty()) {
            val newsItem = newsDataUseCase.searchByTitle(newsTitle)
            if (newsItem != null) {
                newsSearchFoundPrivate.value = newsItem
            } else {
                noSearchFoundPrivate.postValue(Unit)
            }
        } else {
            noSearchFoundPrivate.postValue(Unit)
        }
    }

}