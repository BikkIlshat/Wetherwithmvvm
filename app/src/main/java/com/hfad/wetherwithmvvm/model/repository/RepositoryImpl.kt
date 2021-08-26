package com.hfad.wetherwithmvvm.model.repository


import com.hfad.wetherwithmvvm.model.entities.City
import com.hfad.wetherwithmvvm.model.entities.Weather
import com.hfad.wetherwithmvvm.model.rest.WeatherRepo


class RepositoryImpl : Repository {

    override fun getWeatherFromServer(lat: Double, lng: Double): Weather {
        val dto = WeatherRepo.api.getWeather(lat, lng).execute().body()

        return Weather(
            temperature = dto?.fact?.temp ?: 0,
            feelsLike = dto?.fact?.feelsLike ?: 0,
            condition = dto?.fact?.condition
        )
    }

    override fun getWeatherFromLocalStorageRus() = City.getRussianCities()

    override fun getWeatherFromLocalStorageWorld() = City.getWorldCities()
}
