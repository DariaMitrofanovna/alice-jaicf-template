package com.justai.jaicf.template.trainer.excercises

import com.justai.jaicf.template.trainer.common_models.GeoPoint

object KremlinRoute {
    val points: List<KremlinPoint> = arrayListOf(
        KremlinPoint(
            name = "ворота напротив", // следующая точка - name ...
            runToName = "ворот напротив", // бегите дo runToName ...
            geo = GeoPoint(31.277456, 58.521071)
        ),

        KremlinPoint(
            name = "спортплощадка в 50 метрах", // следующая точка - name ...
            runToName = "спортплощадки с турничками в 50 метрах", // бегите дo runToName ...
            geo = GeoPoint(31.278348, 58.520509)
        ),

        KremlinPoint(
            name = "памятник воинам освободителям", // следующая точка - name ...
            runToName = "площадки под памятником воинам освободителям", // бегите дo runToName ...
            geo = GeoPoint(31.276570, 58.517939)
        ),

        KremlinPoint(
            name = "Спасская башня", // следующая точка - name ...
            runToName = "широкой башни с воротами внизу, она же Спасская башня", // бегите дo runToName ...
            geo = GeoPoint(31.274912, 58.518181)
        ),

        KremlinPoint(
            name = "башня Кокуй", // следующая точка - name ...
            runToName = "высокой башни со шлемовидным куполом, она же башня Кокуй, мимо детских аттракционов слева", // бегите дo runToName ...
            geo = GeoPoint(31.271673, 58.519249)
        ),

        KremlinPoint(
            name = "Златоустовская башня", // следующая точка - name ...
            runToName = "последней башни перед мостом, ведущим в Кремль, она же Златоустовская башня", // бегите дo runToName ...
            geo = GeoPoint(31.274438, 58.521092)
        ),

        KremlinPoint(
            name = "мост в Кремль", // следующая точка - name ...
            runToName = "места, где начинается ближайший мост в Кремль", // бегите дo runToName ...
            geo = GeoPoint(31.273292, 58.521843)
        ),

        KremlinPoint(
            name = "памятник тысячелетию России", // следующая точка - name ...
            runToName = "памятника тысячелетию России справа", // бегите дo runToName ...
            geo = GeoPoint(31.275465, 58.521163)
        )
    )

    val finalLevel = points.size - 1
}