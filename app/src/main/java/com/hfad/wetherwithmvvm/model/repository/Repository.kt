package com.hfad.wetherwithmvvm.model.repository

import com.hfad.wetherwithmvvm.model.entities.Weather

interface Repository {
    fun getWeatherFromServer(): Weather
    fun getWeatherFromLocalStorageRus(): List<Weather>
    fun getWeatherFromLocalStorageWorld(): List<Weather>
}