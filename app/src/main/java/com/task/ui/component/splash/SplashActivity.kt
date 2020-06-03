package com.task.ui.component.splash

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import com.mvvm.sample.databinding.SplashLayoutBinding
import com.task.ui.ViewModelFactory
import com.task.ui.base.BaseActivity
import com.task.ui.base.BaseViewModel
import com.task.ui.component.news.NewsListActivity
import com.task.utils.Constant
import com.task.utils.Constants
import javax.inject.Inject

class SplashActivity : BaseActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    @Inject
    lateinit var splashViewModel: SplashViewModel

    lateinit var binding: SplashLayoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        navigateToMainScreen()
    }

    private fun navigateToMainScreen() {
        Handler().postDelayed({
            startActivity(Intent(this, NewsListActivity::class.java))
            finish()
        }, Constants.SPLASH_DELAY.toLong())
    }

    override fun initializeViewModel() {
        splashViewModel = viewModelFactory.create(SplashViewModel::class.java)
    }

    override fun observeViewModel() {

    }

    override fun initViewBinding() {
        binding = SplashLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


}