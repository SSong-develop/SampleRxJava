package com.example.samplerxjavanetwork.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.samplerxjavanetwork.repository.MainRepository
import io.reactivex.Observable
import okhttp3.ResponseBody
import java.util.*
import java.util.concurrent.Future

class MainViewModel(private val repository: MainRepository) : ViewModel() {

    fun makeFutureQuery(): Future<Observable<ResponseBody>> {
        return repository.makeFutureQuery()
    }

    fun makeQuery() : LiveData<ResponseBody>{
        return repository.makeReactiveQuery()
    }

}