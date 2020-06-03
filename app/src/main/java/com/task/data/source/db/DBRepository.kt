/*
 *  Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      https://mindorks.com/license/apache-v2
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License
 */
package com.task.data.source.db

import com.task.data.model.User
import io.reactivex.Observable
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by amitshekhar on 07/07/17.
 */
@Singleton
class DBRepository @Inject constructor(private val mAppDatabase: AppDatabase) : DbHelper {

    override val allUsers: Observable<List<User?>?>?
        get() = Observable.fromCallable { mAppDatabase.userDao().loadAll() }

    override fun insertUser(user: User?): Observable<Boolean?>? {
        return Observable.fromCallable {
            mAppDatabase.userDao().insert(user)
            true
        }
    }
}