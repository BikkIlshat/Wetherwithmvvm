package com.hfad.wetherwithmvvm.framework.ui.list_of_cities

import android.util.Log
import androidx.lifecycle.*
import com.hfad.wetherwithmvvm.AppState
import com.hfad.wetherwithmvvm.model.repository.Repository
import java.lang.Thread.sleep




class ListOfCitiesViewModel(private val repository: Repository) : ViewModel(), LifecycleObserver {

   val THREAD_SLEEP = 1000
    private val liveDataToObserve: MutableLiveData<AppState> = MutableLiveData()

    fun getLiveData() = liveDataToObserve

    fun getWeatherFromLocalSourceRus() = getDataFromLocalSource(isRussian = true)

    fun getWeatherFromLocalSourceWorld() = getDataFromLocalSource(isRussian = false)


    private fun getDataFromLocalSource(isRussian: Boolean) {
        liveDataToObserve.value = AppState.Loading
        Thread {
            sleep(THREAD_SLEEP.toLong())
            liveDataToObserve.postValue(
                if (isRussian) {
                    AppState.Success(repository.getWeatherFromLocalStorageRus())
                } else  {
                    AppState.Success(repository.getWeatherFromLocalStorageWorld())
                }
            )
        }.start()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    private fun onViewStart() {
        Log.i("LifecycleEvent", "onStart")
    }
}