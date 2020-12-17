package com.example.samplerxjavanetwork.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import com.example.samplerxjavanetwork.remote.service.RetrofitService
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import java.util.concurrent.*

class MainRepository(
    private val retrofitService: RetrofitService
) {

    // using fromFuture
    fun makeFutureQuery(): Future<Observable<ResponseBody>> {
        val executor: ExecutorService = Executors.newSingleThreadExecutor()
        val myNetworkCallable = Callable<Observable<ResponseBody>> {
            retrofitService.makeObservableQuery()
        }

        return object : Future<Observable<ResponseBody>> {
            override fun cancel(mayInterruptIfRunning: Boolean): Boolean {
                if (mayInterruptIfRunning) {
                    executor.shutdown()
                }
                return false
            }

            override fun isCancelled(): Boolean = executor.isShutdown

            override fun isDone(): Boolean = executor.isTerminated

            override fun get(): Observable<ResponseBody> = executor.submit(myNetworkCallable).get()

            override fun get(timeout: Long, timeunit: TimeUnit?): Observable<ResponseBody> =
                executor.submit(myNetworkCallable).get(timeout, timeunit)
        }
    }

    // using fromPublisher
    fun makeReactiveQuery(): LiveData<ResponseBody> {
        return LiveDataReactiveStreams.fromPublisher(
            retrofitService.makeQuery().subscribeOn(Schedulers.io())
        )
    }

}