package com.task.data.source.pref

import com.orhanobut.hawk.Hawk
import com.task.utils.CacheKey
import javax.inject.Inject

class PrefRepository @Inject constructor() {

    fun getOverview(): String? = Hawk.get(CacheKey.OVERVIEW, null)

    fun setOverview(covidOverview: String) = Hawk.put(CacheKey.OVERVIEW, covidOverview)

}