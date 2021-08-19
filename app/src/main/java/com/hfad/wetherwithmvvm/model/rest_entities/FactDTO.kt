package com.hfad.wetherwithmvvm.model.rest_entities

import java.time.temporal.Temporal

data class FactDTO(
    val temp: Int?,
    val feels_like: Int?,
    val condition: String?
)
