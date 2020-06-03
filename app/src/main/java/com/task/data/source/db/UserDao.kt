///*
// *  Copyright (C) 2017 MINDORKS NEXTGEN PRIVATE LIMITED
// *
// *  Licensed under the Apache License, Version 2.0 (the "License");
// *  you may not use this file except in compliance with the License.
// *  You may obtain a copy of the License at
// *
// *      https://mindorks.com/license/apache-v2
// *
// *  Unless required by applicable law or agreed to in writing, software
// *  distributed under the License is distributed on an "AS IS" BASIS,
// *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// *  See the License for the specific language governing permissions and
// *  limitations under the License
// */
//package com.task.data.source.db
//
//import androidx.room.*
//import com.task.data.source.db.User
//import io.reactivex.Single
//
//
///**
// * Created by amitshekhar on 07/07/17.
// */
//@Dao
//interface UserDao {
//
//    @Delete
//    fun delete(user: User?)
//
//    @Query("SELECT * FROM users WHERE name LIKE :name LIMIT 1")
//    fun findByName(name: String?): Single<User?>?
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insert(user: User?)
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    fun insertAll(users: List<User?>?)
//
//    @Query("SELECT * FROM users")
//    fun loadAll(): List<User?>?
//
//    @Query("SELECT * FROM users WHERE id IN (:userIds)")
//    fun loadAllByIds(userIds: List<Int?>?): List<User?>?
//}