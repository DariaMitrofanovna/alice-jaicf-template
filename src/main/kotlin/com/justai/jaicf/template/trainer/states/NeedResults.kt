package com.justai.jaicf.template.trainer.states

import com.justai.jaicf.channel.yandexalice.AliceReactions
import com.justai.jaicf.channel.yandexalice.api.AliceBotRequest
import com.justai.jaicf.channel.yandexalice.api.model.Image
import com.justai.jaicf.template.res.Images
import com.justai.jaicf.template.trainer.RandomPhrase
import com.justai.jaicf.template.trainer.common_models.GeoPoint
import com.justai.jaicf.template.trainer.common_models.RandomPhrasesRepository
import com.justai.jaicf.template.util.intent.SimpleIntent
import com.justai.jaicf.template.util.intent.hasSimpleIntent

class NeedResults(private val path: List<GeoPoint?>) : State() {
    override fun handleInternal(request: AliceBotRequest, alice: AliceReactions): State {
        println("agon: need results")
        val distance = distance(path)
        val text = if (request.hasSimpleIntent(SimpleIntent.YANDEX_CONFIRM)) {
            """
               Вы сделали ${path.size} разных силовых упражнений и пробежали $distance.
               Классно позанимались! Повторим?
            """.trimIndent()
        } else {
            """
               Классно позанимались! Повторим? 
            """.trimIndent()
        }

        val tts = if (request.hasSimpleIntent(SimpleIntent.YANDEX_CONFIRM)) {
            """
               Вы сделали ${path.size} разных силовых упражнений и пробежали $distance.
               Классно позанимались! Повтор+им?
            """.trimIndent()
        } else {
            """
               Классно позанимались! Повтор+им? 
            """.trimIndent()
        }

        alice.say(text, tts)
        alice.buttons("О да!", "Не сегодня") // final suggests
        alice.image(
            Image(Images.happyEnd, "Классно позанимались, повторим?"),
        )

        return RepeatToBeginning()
    }

    fun distance(path: List<GeoPoint?>): String {
        if (path.all { it == null }) {
            return "прилично, но без геолокации посчитать было нельзя"
        }

        return when (path.size) {
            in 0..2 -> RandomPhrase(
                "несколько сот метров",
                "совсем немного"
            )
            in 3..5 -> RandomPhrase(
                "около километра",
                "километр с небольшим",
                "900 метров"
            )

            in 6..8 -> RandomPhrase(
                "полтора километра",
                "чуть меньше двух километров"
            )

            in 9..10 -> RandomPhrase(
                "больше двух километров",
                "два с половиной километра",
                "почти три километра"
            )

            else -> RandomPhrase(
                "довольно много",
                "приличное расстояние"
            )
        }.random
    }
}