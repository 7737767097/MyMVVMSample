package com.task.ui.component.details

import android.os.Bundle
import com.mvvm.sample.R
import com.mvvm.sample.databinding.DetailsLayoutBinding
import com.squareup.picasso.Picasso
import com.task.data.model.NewsItem
import com.task.ui.ViewModelFactory
import com.task.ui.base.BaseActivity
import com.task.utils.Constants
import com.task.utils.observe
import javax.inject.Inject

class DetailsActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var detailsViewModel: DetailsViewModel

    private lateinit var binding: DetailsLayoutBinding

    override fun initializeViewModel() {
        detailsViewModel = viewModelFactory.create(DetailsViewModel::class.java)
    }

    override fun initViewBinding() {
        binding = DetailsLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        detailsViewModel.newsItem.value = intent.getParcelableExtra(Constants.NEWS_ITEM_KEY)
    }

    override fun observeViewModel() {
        observe(detailsViewModel.newsItem, ::initialzeView)
    }

    private fun initialzeView(newsItem: NewsItem) {
        binding.tvTitle.text = newsItem.title
        binding.tvDescription.text = newsItem.abstractInfo
        if (!(newsItem.multimedia.isNullOrEmpty())) {
            Picasso.get().load(newsItem.multimedia.last().url).placeholder(R.drawable.news)
                .into(binding.ivNewsMainImage)
        }
    }


}