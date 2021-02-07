package com.justai.jaicf.template.trainer.common_models

import com.justai.jaicf.channel.yandexalice.api.Location

data class GeoPoint(
    val lat: Double,
    val lon: Double,
) {
    companion object {
        fun fromLocation(location: Location): GeoPoint {
            return GeoPoint(location.lat, location.lon)
        }
    }
}