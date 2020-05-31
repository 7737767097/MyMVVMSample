package com.task.data.error

import java.lang.Exception

class Error(val code: Int, val description: String) {

    constructor(exeception: Exception) : this(DEFAULT_ERROR, exeception.message ?: "")

    companion object {
        const val NO_INTERNET_CONNECTION = -1
        const val NETWORK_ERROR = -2
        const val DEFAULT_ERROR = -3
    }
}