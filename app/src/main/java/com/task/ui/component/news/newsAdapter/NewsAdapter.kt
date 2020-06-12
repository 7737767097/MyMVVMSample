package com.task.ui.component.news.newsAdapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mvvm.sample.databinding.NewsItemBinding
import com.task.data.model.NewsItem
import com.task.ui.base.listeners.RecyclerItemListener
import com.task.ui.component.news.NewsListViewModel

class NewsAdapter(
    private val newsListViewModel: NewsListViewModel,
    private val news: List<NewsItem>
) : RecyclerView.Adapter<NewsViewHolder>() {

    private val onItemClickListener: RecyclerItemListener = object : RecyclerItemListener {
        override fun onItemSelected(newsItem: NewsItem) {
            newsListViewModel.openNewsDetails(newsItem)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val itemBinding =
            NewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NewsViewHolder(itemBinding)
    }

    override fun getItemCount(): Int {
        return news.size
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bind(news[position], onItemClickListener)
    }
}