package com.hfad.wetherwithmvvm.model.repository

import com.hfad.wetherwithmvvm.model.entities.Weather
import com.hfad.wetherwithmvvm.model.entities.getRussianCities
import com.hfad.wetherwithmvvm.model.entities.getWorldCities

class RepositoryImpl: Repository {

    override fun getWeatherFromServer() = Weather()

    override fun getWeatherFromLocalStorageRus() = getRussianCities()

    override fun getWeatherFromLocalStorageWorld() = getWorldCities()
}

