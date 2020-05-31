package com.task.data.error.mapper

import com.mvvm.sample.R
import com.task.App
import com.task.data.error.Error
import javax.inject.Inject

class ErrorMapper @Inject constructor() : ErrorMapperInterface {

    override fun getErrorString(errorId: Int): String {
        return App.mContext.getString(errorId)
    }

    override val errorMap: Map<Int, String>
        get() = mapOf(
            Pair(Error.NETWORK_ERROR, getErrorString(R.string.network_error)),
            Pair(Error.NO_INTERNET_CONNECTION, getErrorString(R.string.no_internet))
        ).withDefault { getErrorString(R.string.network_error) }
}