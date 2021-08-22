package com.hfad.wetherwithmvvm.model.repository

import com.hfad.wetherwithmvvm.model.WeatherLoader
import com.hfad.wetherwithmvvm.model.entities.City
import com.hfad.wetherwithmvvm.model.entities.Weather


class RepositoryImpl: Repository {

    override fun getWeatherFromServer(lat: Double, lng: Double): Weather {
        val dto = WeatherLoader.loadWeather(lat, lng)
        return Weather(
            temperature = dto?.fact?.temp ?: 0,
            feelsLike = dto?.fact?.feels_like ?: 0,
            condition = dto?.fact?.condition
        )
    }

    override fun getWeatherFromLocalStorageRus() = City.getRussianCities()

    override fun getWeatherFromLocalStorageWorld() = City.getWorldCities()
}

