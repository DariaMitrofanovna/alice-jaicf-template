package com.justai.jaicf.template.trainer.excercises

import com.justai.jaicf.template.trainer.common_models.GeoPoint

object KremlinRoute {
    val points: List<KremlinPoint> = arrayListOf(
        KremlinPoint(
            name = "ворота напротив", // следующая точка - name ...
            runToName = "ворот напротив", // бегите дo runToName ...
            geo = GeoPoint(0.0, 0.0)
        ),
        KremlinPoint(
            name = "спортплощадка в 50 метрах", // следующая точка - name ...
            runToName = "спортплощадки в 50 метрах", // бегите дo runToName ...
            geo = GeoPoint(0.0, 0.0)
        ),
        KremlinPoint(
            name = "памятник воинам освободителям", // следующая точка - name ...
            runToName = "памятника воинам освободителям", // бегите дo runToName ...
            geo = GeoPoint(0.0, 0.0)
        ),
        KremlinPoint(
            name = "локация 1", // следующая точка - name ...
            runToName = "локации 2", // бегите дo runToName ...
            geo = GeoPoint(0.0, 0.0)
        ),
    )

    val finalLevel = points.size - 1
}