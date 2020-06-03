package com.task.ui.base.listeners

import com.task.data.model.NewsItem

interface RecyclerItemListener {

    fun onItemSelected(newsItem: NewsItem)
}