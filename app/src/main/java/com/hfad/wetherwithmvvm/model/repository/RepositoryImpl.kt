package com.hfad.wetherwithmvvm.model.repository

import com.hfad.wetherwithmvvm.model.entities.City
import com.hfad.wetherwithmvvm.model.entities.Weather


class RepositoryImpl: Repository {

    override fun getWeatherFromServer() = Weather()

    override fun getWeatherFromLocalStorageRus() = City.getRussianCities()

    override fun getWeatherFromLocalStorageWorld() = City.getWorldCities()
}

