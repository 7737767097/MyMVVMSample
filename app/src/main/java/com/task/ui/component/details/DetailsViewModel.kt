package com.task.ui.component.details

import androidx.lifecycle.MutableLiveData
import com.task.data.error.mapper.ErrorMapper
import com.task.data.model.NewsItem
import com.task.ui.base.BaseViewModel
import com.task.usecase.errors.ErrorManager
import javax.inject.Inject

class DetailsViewModel @Inject constructor() : BaseViewModel() {

    var newsItem: MutableLiveData<NewsItem> = MutableLiveData()

    override val errorManager: ErrorManager
        get() = ErrorManager(ErrorMapper())

}