package com.hfad.wetherwithmvvm.model.repository

import com.hfad.wetherwithmvvm.model.entities.Weather

class RepositoryImpl: Repository {
    override fun getWeatherFromServer()=  Weather()
    override fun getWeatherFromLocalStorage() = Weather()
}