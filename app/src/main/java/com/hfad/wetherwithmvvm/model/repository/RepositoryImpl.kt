package com.hfad.wetherwithmvvm.model.repository


import com.hfad.wetherwithmvvm.model.database.Database
import com.hfad.wetherwithmvvm.model.database.HistoryEntity
import com.hfad.wetherwithmvvm.model.entities.City
import com.hfad.wetherwithmvvm.model.entities.Weather
import com.hfad.wetherwithmvvm.model.rest.WeatherRepo

private const val LATITUDE = 0.0
private const val LONGITUDE = 0.0
private const val FEELSLIKE = 0


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

    override fun getAllHistory(): List<Weather> =
        convertHistoryEntityToWeather(Database.db.historyDao().all())


    override fun saveEntity(weather: Weather) {
        Database.db.historyDao().insert(convertWeatherToEntity(weather))
    }

    private fun convertHistoryEntityToWeather(entityList: List<HistoryEntity>): List<Weather> =
        entityList.map {
            Weather(City(it.city, LATITUDE, LONGITUDE), it.temperature, FEELSLIKE, it.condition)
        }


    private fun convertWeatherToEntity(weather: Weather): HistoryEntity =
        HistoryEntity(0, weather.city.city,
            weather.temperature ?: 0,
            weather.condition ?: ""
        )
}


