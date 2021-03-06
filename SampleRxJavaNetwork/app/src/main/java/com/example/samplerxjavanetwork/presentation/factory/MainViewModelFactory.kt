package com.example.samplerxjavanetwork.presentation.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.samplerxjavanetwork.presentation.viewmodel.MainViewModel
import com.example.samplerxjavanetwork.repository.MainRepository

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(private val mainRepository: MainRepository) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        require(modelClass.isAssignableFrom(MainViewModel::class.java)){"unknown class name"}
        return MainViewModel(mainRepository) as T
    }
}