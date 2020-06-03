package com.task.ui.base

import androidx.lifecycle.ViewModel
import com.task.usecase.errors.ErrorManager

abstract class BaseViewModel : ViewModel() {
    abstract val errorManager: ErrorManager
}