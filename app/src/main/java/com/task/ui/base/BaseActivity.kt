package com.task.ui.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.mvvm.sample.databinding.ToolbarBinding
import com.task.ui.ViewModelFactory
import com.task.ui.base.listeners.ActionBarView
import com.task.ui.base.listeners.BaseView
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import kotlinx.android.synthetic.main.toolbar.*
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity(), BaseView, ActionBarView {

    protected lateinit var baseViewModel: BaseViewModel
    protected lateinit var toolbarBinding: ToolbarBinding

    protected abstract fun initializeViewModel()
    abstract fun observeViewModel()
    protected abstract fun initViewBinding()

    override fun onCreate(savedInstanceState: Bundle?) {
        AndroidInjection.inject(this)
        super.onCreate(savedInstanceState)
        initViewBinding()
        initializeToolbar()
        initializeViewModel()
        observeViewModel()
    }

    private fun initializeToolbar() {
        toolbarBinding = ToolbarBinding.inflate(layoutInflater)
        toolbarBinding.txtToolbarTitle.text = ""
    }

    override fun setUpIconVisibility(visible: Boolean) {
        supportActionBar?.setDisplayHomeAsUpEnabled(visible)
    }

    override fun setTitle(title: String) {
        toolbarBinding.txtToolbarTitle.text = title
    }

    override fun setSettingsIconVisibility(visibility: Boolean) {
        toolbarBinding.icToolbarSetting.visibility = if (visibility) View.VISIBLE else View.GONE
    }

    override fun setRefreshVisibility(visibility: Boolean) {
        toolbarBinding.icToolbarRefresh.visibility = if (visibility) View.VISIBLE else View.GONE
    }
}