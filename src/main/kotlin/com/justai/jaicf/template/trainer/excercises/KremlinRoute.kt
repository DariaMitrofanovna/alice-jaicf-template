package com.justai.jaicf.template.trainer.excercises

import com.justai.jaicf.template.trainer.common_models.GeoPoint

object KremlinRoute {
    val points: List<KremlinPoint> = arrayListOf(
        KremlinPoint(
            name = "ворота напротив", // следующая точка - name ...
            runToName = "до ворот напротив", // бегите дo runToName ...
            geo = GeoPoint(31.277456, 58.521071)
        ),

        KremlinPoint(
            name = "спортплощадка в 50 метрах (выход из ворот - направо, вниз по дороге вдоль стены)", // следующая точка - name ...
            runToName = "из ворот, поворачивайте направо, и до спортплощадки с турник+ами в 50 метрах", // бегите дo runToName ...
            geo = GeoPoint(31.278348, 58.520509)
        ),

        KremlinPoint(
            name = "памятник Воинам Освободителям", // следующая точка - name ...
            runToName = "до площадки перед памятником Воинам Освободителям, не добегая до огромного коня метров 50", // бегите дo runToName ...
            geo = GeoPoint(31.276570, 58.517939)
        ),

        KremlinPoint(
            name = "точка на тропе напротив Спасской башни (бежим по тропинке по краю рва, по другую сторону от Кремля)", // следующая точка - name ...
            runToName = "по тропинке по краю рва, до широкой башни с воротами внизу, она же Спасская башня (через ров перелезать не нужно, оставайтесь по другую сторону от Крмля)", // бегите дo runToName ...
            geo = GeoPoint(31.274912, 58.518181)
        ),

        KremlinPoint(
            name = "башня Кокуй (бежим по нашей тропинке вдоль рва)", // следующая точка - name ...
            runToName = "по нашей тропинке, остановитесь напротив высокой башни со шлемовидным куполом, она же башня Кокуй", // бегите дo runToName ...
            geo = GeoPoint(31.271673, 58.519249)
        ),

        KremlinPoint(
            name = "Златоустовская башня", // следующая точка - name ...
            runToName = "по нашей тропинке до последней башни перед мостом, ведущим в Кремль, она же Златоустовская башня", // бегите дo runToName ...
            geo = GeoPoint(31.274438, 58.521092)
        ),

        KremlinPoint(
            name = "мост в Кремль", // следующая точка - name ...
            runToName = "до начала моста, ведущего через ров в Кремль", // бегите дo runToName ...
            geo = GeoPoint(31.273292, 58.521843)
        ),

        KremlinPoint(
            name = "памятник тысячелетию России, конечная", // следующая точка - name ...
            runToName = "до памятника тысячелетию России, это в самом центре Кремля", // бегите дo runToName ...
            geo = GeoPoint(31.275465, 58.521163)
        )
    )

    val finalLevel = points.size - 1
}