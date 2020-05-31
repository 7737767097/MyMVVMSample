package com.task.data

import com.task.data.model.NewsModel
import com.task.data.source.db.DBRepository
import com.task.data.source.db.User
import com.task.data.source.pref.PrefRepository
import com.task.data.source.remote.RemoteRepository
import io.reactivex.Observable
import javax.inject.Inject

class DataRepository @Inject constructor(
    val remoteRepository: RemoteRepository,
    val prefRepository: PrefRepository,
    val dbRepository: DBRepository
) : DataSource {

    //    Remote method (From API)
    override suspend fun requestNews(): Resource<NewsModel> {
        return remoteRepository.requestNews()
    }

    //    Preference method
    override suspend fun getOverView(): String {
        return prefRepository.getOverview()!!
    }

    //    Database method using RoomDB
    override suspend fun getAllUser(): Observable<List<User?>?>? {
        return dbRepository.allUsers
    }


}