package com.task.ui.base.listeners

interface ActionBarView {
    fun setUpIconVisibility(visible: Boolean)
    fun setTitle(title: String)
    fun setSettingsIconVisibility(visibility: Boolean)
    fun setRefreshVisibility(visibility: Boolean)
}