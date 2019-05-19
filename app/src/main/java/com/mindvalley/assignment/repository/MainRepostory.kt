package com.mindvalley.assignment.repository

import android.arch.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.mindvalley.assignment.model.UserInfoDTO
import com.mindvalley.assignment.downloader.FetchData

class MainRepository() {

    private val gson = Gson()
    val pinList = MutableLiveData<MutableList<UserInfoDTO>>()

    init {
        pinList.value= mutableListOf()
    }
    fun fetchDataFromServer(isRefreshing: MutableLiveData<Boolean>) {
        FetchData("http://pastebin.com/raw/wgkJgazE").load { result, throwable->
            if(throwable!=null)
            {
                isRefreshing.value=false
                return@load
            }

            try
            {
                val items = mutableListOf<UserInfoDTO>()
                val array =  gson.fromJson(result, JsonArray::class.java)
                array.mapTo(items) {
                    val data = it.asJsonObject
                    UserInfoDTO.fromJsonObject(data)
                }
                pinList.value = items
            }
            catch(e:Exception)
            {
            }
            isRefreshing.value=false
        }

    }

}