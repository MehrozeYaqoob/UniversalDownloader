package com.mindvalley.assignment.viewmodel

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.mindvalley.assignment.repository.MainRepository

class MainViewModel : ViewModel() {
    val isRefreshing = MutableLiveData<Boolean>()
    val repository : MainRepository = MainRepository()


    init {
        isRefreshing.value = false
    }

    fun getDataFromServer() {
        isRefreshing.value = true
        repository.fetchDataFromServer(isRefreshing)
    }

}