package com.justai.jaicf.template.trainer.excercises

import com.justai.jaicf.template.trainer.common_models.GeoPoint

object KremlinRoute {
    val points: List<KremlinPoint> = arrayListOf(
        KremlinPoint(runToName = "шапки мономаха", GeoPoint(31.256607, 58.529699)),
        KremlinPoint(runToName = "памятника тысячелетию России", GeoPoint(31.256607, 58.529699)),
        KremlinPoint(runToName = "общественного туалета", GeoPoint(31.256607, 58.529699)),
    )

    val finalLevel = points.size - 1
}