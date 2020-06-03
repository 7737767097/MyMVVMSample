package com.task.data

import android.util.Log
import com.google.gson.Gson
import com.task.data.model.NewsModel

import com.task.data.source.pref.PrefRepository
import com.task.data.source.remote.RemoteRepository
import io.reactivex.Observable
import javax.inject.Inject

class DataRepository @Inject constructor(
    val remoteRepository: RemoteRepository,
    val prefRepository: PrefRepository
    /*, val dbRepository: DBRepository*/
) : DataSource {

    //    Remote method (From API)
    override suspend fun requestNews(): Resource<NewsModel> {
        if (prefRepository.getNews().isNullOrEmpty()) {
            Log.d("test", "from remote")
            if (remoteRepository.requestNews()?.data != null)
                prefRepository.setNews(Gson().toJson(remoteRepository.requestNews()?.data))
            return remoteRepository.requestNews()
        } else {
            Log.d("test", "from preference")
            return Resource.Success(
                Gson().fromJson(
                    prefRepository.getNews(),
                    NewsModel::class.java
                )
            )
        }
    }

    //    Preference method
    override suspend fun getOverView(): String {
        return prefRepository.getOverview()!!
    }

//    //    Database method using RoomDB
//    override suspend fun getAllUser(): Observable<List<User?>?>? {
//        return dbRepository.allUsers
//    }


}