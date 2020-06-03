package com.task.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.mvvm.sample.R
import com.task.ui.base.listeners.BaseView

abstract class BaseFragment : Fragment(), BaseView {

    protected lateinit var baseViewModel: BaseViewModel

    abstract val layoutId: Int

    protected abstract fun initializeDagger()

    protected abstract fun initializePresenter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initializeDagger()
        initializePresenter()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(layoutId, container, false)
    }

    fun setTitle(title: String) {
        val actionBar = (activity as BaseActivity).supportActionBar
        if (actionBar != null) {
            val titleTextView = activity?.findViewById<TextView>(R.id.txt_toolbar_title)
            if (titleTextView != null && title.isNotEmpty()) {
                titleTextView.text = title
            }
        }
    }

}