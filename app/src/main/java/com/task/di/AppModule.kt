/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.task.di

import android.app.Application

import com.task.data.source.pref.PrefRepository
import com.task.data.source.remote.RemoteRepository
import com.task.data.source.remote.ServiceGenerator

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Module
class AppModule {

    @Provides
    fun provideApplication(): Application {
        return Application()
    }

    @Provides
    @Singleton
    fun providePrefRepository(): PrefRepository {
        return PrefRepository()
    }

    @Provides
    @Singleton
    fun provideRemoteRepository(): RemoteRepository {
        return RemoteRepository(ServiceGenerator())
    }

    @Provides
    @Singleton
    fun provideCoroutineContext(): CoroutineContext {
        return Dispatchers.Main
    }

//    @Provides
//    @DatabaseInfo
//    fun provideDataBaseName(): String {
//        return Constants.DB_NAME
//    }
//
//    @Provides
//    @Singleton
//    fun provideAppDatabase(
//        @DatabaseInfo dbName: String?,
//        context: Context?
//    ): AppDatabase {
//        return Room.databaseBuilder(context!!, AppDatabase::class.java, dbName!!)
//            .fallbackToDestructiveMigration()
//            .build()
//    }
//
//    @Provides
//    @Singleton
//    fun provideDBRepository(): DBRepository {
//        return DBRepository(
//            provideAppDatabase(
//                provideDataBaseName(),
//                provideApplication().applicationContext
//            )
//        )
//    }
}
