package com.task.ui.component.news

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.mvvm.sample.R
import com.mvvm.sample.databinding.HomeActivityBinding
import com.task.data.Resource
import com.task.data.model.NewsItem
import com.task.data.model.NewsModel
import com.task.ui.ViewModelFactory
import com.task.ui.base.BaseActivity
import com.task.ui.component.details.DetailsActivity
import com.task.ui.component.news.newsAdapter.NewsAdapter
import com.task.utils.*
import javax.inject.Inject

class NewsListActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var newsListViewModel: NewsListViewModel

    private lateinit var binding: HomeActivityBinding

    override fun initializeViewModel() {
        newsListViewModel = viewModelFactory.create(NewsListViewModel::class.java)
    }

    override fun initViewBinding() {
        binding = HomeActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.toolbarLayout.icToolbarRefresh.setOnClickListener { newsListViewModel.getNews() }
        binding.toolbarLayout.icToolbarSetting.setOnClickListener {
            if (!(binding.etSearch.text?.toString().isNullOrEmpty())) {
                binding.pbLoading.visibility = View.VISIBLE
                newsListViewModel.onSearchClick(binding.etSearch.text?.toString()!!)
            }
        }
        binding.rvNewsList.layoutManager = LinearLayoutManager(this)
        binding.rvNewsList.setHasFixedSize(true)
        newsListViewModel.getNews()
    }

    override fun observeViewModel() {
        observe(newsListViewModel.newsLiveData, ::handleNewsList)
        observe(newsListViewModel.newsSearchFound, ::showSearchResult)
        observe(newsListViewModel.noSearchFound, ::noSearchResult)
        observe(newsListViewModel.openNewsDetails, ::navigateToDetailsScreen)
        observeSnackBarMessages(newsListViewModel.showSnackBar)
        observeToast(newsListViewModel.showToast)
    }

    private fun observeToast(showToast: LiveData<Event<Any>>) {
        binding.rlNewsList.showToast(this, showToast, Toast.LENGTH_LONG)
    }

    private fun observeSnackBarMessages(showSnackBar: LiveData<Event<Int>>) {
        binding.rlNewsList.setupSnackbar(this, showSnackBar, Snackbar.LENGTH_LONG)
    }

    private fun navigateToDetailsScreen(event: Event<NewsItem>?) {
        event?.getContentIfNotHandled()?.let {
            val intent = Intent(this, DetailsActivity::class.java).apply {
                putExtra(Constants.NEWS_ITEM_KEY, it)
            }
            startActivity(intent)
        }

    }

    private fun noSearchResult(unit: Unit) {
        showSearchError()
        binding.pbLoading.toGone()
    }

    private fun showSearchError() {
        newsListViewModel.showSnackBarMessage(R.string.search_error)
    }

    private fun showSearchResult(newsItem: NewsItem) {
        newsListViewModel.openNewsDetails(newsItem)
        binding.pbLoading.toGone()
    }

    private fun handleNewsList(newsModel: Resource<NewsModel>) {
        when (newsModel) {
            is Resource.Loading -> showLoadingView()
            is Resource.Success -> newsModel.data?.let { bindListData(newsModel = it) }
            is Resource.DataError -> {
                showDataView(false)
                newsModel.errorCode?.let { newsListViewModel.showToastMessage(it) }
            }
        }
    }

    private fun showLoadingView() {
        binding.pbLoading.toVisible()
        binding.tvNoData.toGone()
        binding.rlNewsList.toGone()
        EspressoIdlingResource.increment()
    }

    private fun bindListData(newsModel: NewsModel) {
        if (!(newsModel.newsItems.isNullOrEmpty())) {
            val newsAdapter = NewsAdapter(newsListViewModel, newsModel.newsItems)
            binding.rvNewsList.adapter = newsAdapter
            showDataView(true)
        } else {
            showDataView(false)
        }
    }

    private fun showDataView(show: Boolean) {
        binding.tvNoData.visibility = if (show) View.GONE else View.VISIBLE
        binding.rlNewsList.visibility = if (show) View.VISIBLE else View.GONE
        binding.pbLoading.toGone()
    }

}