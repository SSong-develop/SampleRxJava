package com.example.samplerxjavanetwork.remote.service

import io.reactivex.Flowable
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET

interface RetrofitService {

    // fromFuture Example
    @GET("todos/1")
    fun makeObservableQuery() : Observable<ResponseBody>

    // fromPublisher Example
    @GET("todos/1")
    fun makeQuery() : Flowable<ResponseBody>
}