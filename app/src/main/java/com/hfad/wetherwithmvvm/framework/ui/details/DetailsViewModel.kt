package com.hfad.wetherwithmvvm.framework.ui.details

import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hfad.wetherwithmvvm.AppState
import com.hfad.wetherwithmvvm.model.repository.Repository

class DetailsViewModel(private val repository: Repository) : ViewModel(), LifecycleObserver {
    val liveDataObserver: MutableLiveData<AppState> = MutableLiveData()

    fun loadData(lat: Double, lng: Double) {
        liveDataObserver.value = AppState.Loading
        Thread {
            val data = repository.getWeatherFromServer(lat, lng)
            repository.saveEntity(data)
            liveDataObserver.postValue(AppState.Success(listOf(data)))
        }.start()
    }
}