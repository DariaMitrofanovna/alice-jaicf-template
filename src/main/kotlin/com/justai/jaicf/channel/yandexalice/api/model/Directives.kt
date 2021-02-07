package com.justai.jaicf.channel.yandexalice.api.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonObject

@Serializable
data class Directives(
    @SerialName("request_geolocation")
    val requestGeolocation: JsonObject? = null
)