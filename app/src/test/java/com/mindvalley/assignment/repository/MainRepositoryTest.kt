package com.mindvalley.assignment.repository

import android.arch.lifecycle.MutableLiveData
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.runners.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class MainRepositoryTest {

    lateinit var repository: MainRepository
    var isRefereshing: MutableLiveData<Boolean> = MutableLiveData()


    @Test
    fun fetchDataFromServer() {
        repository = MainRepository()
        isRefereshing.value = true
        repository.fetchDataFromServer(isRefereshing)

    }
}