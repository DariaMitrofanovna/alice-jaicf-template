package com.justai.jaicf.template.trainer.excercises

import com.justai.jaicf.template.trainer.common_models.GeoPoint

data class KremlinPoint(
    val name: String,
    val runToName: String,
    val geo: GeoPoint
)