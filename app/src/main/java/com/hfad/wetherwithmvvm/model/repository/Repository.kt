package com.hfad.wetherwithmvvm.model.repository

import com.hfad.wetherwithmvvm.model.entities.Weather

interface Repository {
    fun getWeatherFromServer(): Weather
    fun getWeatherFromLocalStorage(): Weather
}