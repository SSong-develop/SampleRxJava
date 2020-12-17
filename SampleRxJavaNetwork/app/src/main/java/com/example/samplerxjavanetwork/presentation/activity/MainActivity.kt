package com.example.samplerxjavanetwork.presentation.activity

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.samplerxjavanetwork.R
import com.example.samplerxjavanetwork.presentation.factory.MainViewModelFactory
import com.example.samplerxjavanetwork.presentation.viewmodel.MainViewModel
import com.example.samplerxjavanetwork.remote.api.RetrofitBuilder
import com.example.samplerxjavanetwork.repository.MainRepository
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.ResponseBody
import kotlin.Exception

// RxJava Call Adapter dependency need to get api's response Observable or Flowable
// Future 와 fromFuture 연산자가 어떻게 진행되는지에 대한 예제일 뿐 그닥 그렇게 권장하는 스타일은 아닌듯 싶다.
const val TAG = "MainActivity"

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViewModel()

        // fromFuture
        try{
            viewModel.makeFutureQuery().get()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(object : Observer<ResponseBody>{
                        override fun onSubscribe(d: Disposable) {
                            Log.d(TAG,"on Subscribe: called.")
                        }

                        override fun onNext(responseBody: ResponseBody) {
                            Log.d(TAG,"onNext: got the response from server!")
                            try {
                                Log.d(TAG, "onNext: $responseBody")
                            } catch (e : Exception){
                                e.printStackTrace()
                            }
                        }

                        override fun onError(e: Throwable) {
                            Log.e(TAG,"onError: $e")
                        }

                        override fun onComplete() {
                            Log.e(TAG,"onComplete: called")
                        }
                    })
        }catch (e : Exception){
            e.printStackTrace()
        }

        // fromPublisher
        try{
            viewModel.makeQuery().observe(this){
                Log.d(TAG,it.toString())
            }
        }catch (e : Exception){
            e.printStackTrace()
        }

    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this@MainActivity, MainViewModelFactory(MainRepository(RetrofitBuilder.retrofitService))).get(MainViewModel::class.java)
    }

}