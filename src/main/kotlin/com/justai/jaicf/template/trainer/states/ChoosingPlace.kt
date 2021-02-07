package com.justai.jaicf.template.trainer.states

import com.justai.jaicf.channel.yandexalice.AliceReactions
import com.justai.jaicf.channel.yandexalice.api.AliceBotRequest
import com.justai.jaicf.template.util.intent.SimpleIntent
import com.justai.jaicf.template.util.intent.hasSimpleIntent

class ChoosingPlace : State() {
    override val fallbackTexts: List<String> = listOf(
        "Не до конца Вас поняла. где будете тренироваться?",
        "Чтобы потренироваться, нужно выбрать где. Скажите Кремль или свое место.",
        "Извините, не поняла Вас. Начнем с начала?"
    )

    override fun handleInternal(request: AliceBotRequest, alice: AliceReactions): State {
        return if (request.hasSimpleIntent(SimpleIntent.KREMLIN) || request.input == ("вокруг кремля")) {
            kremlin(request, alice)
        } else if (request.hasSimpleIntent(SimpleIntent.MY_CHOICE) || request.input == ("своё место")) {
            alice.say("Скажите, когда будете на месте")
            alice.buttons("на месте!")
            GettingToStartPlace(kremlin = false)
        } else {
            fallback(request, alice)
            if (fallbackDepth == 3) {
                fallbackDepth = 0
                alice.buttons("заново")
                return End()
            }
            return this
        }
//            if (userUtil.hasGeoLocation(request.clientId)) {
//                alice.say("Когда будете на точке старта, скажите \"Готов!\", и мы начнем тренировку.")
//                return TrainingStart()
//            } else {
//                if (userUtil.isBesideStart(request.clientId)) {
//                    alice.say("Вы на точке старта. Скажите \"Готов!\", и мы начнем тренировку!")
//                    return TrainingStart()
//                } else {
//                    alice.say("Вы далеко от точки старта. Сначала нужно до нее добраться! Можете воспользоваться маршрутом до нужной точки  или пойти своим путем. Скажите \"Готов!\", и мы начнем тренировку.")
//                return CheckingLocation()
//                }
//            }
    }

    private fun kremlin(request: AliceBotRequest, alice: AliceReactions): State {
        alice.say(
            """
                Когда будете на точке старта, скажите "я на месте", и мы начнем тренировку.
            """.trimIndent()
        )
        alice.buttons("Я на месте")
        alice.endSession()
        return GettingToStartPlace(kremlin = true)
    }
}

