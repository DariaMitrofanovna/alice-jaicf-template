package com.justai.jaicf.template.trainer.states

import com.justai.jaicf.channel.yandexalice.api.AliceBotRequest
import com.justai.jaicf.channel.yandexalice.AliceReactions
import com.justai.jaicf.template.util.intent.SimpleIntent
import com.justai.jaicf.template.util.intent.hasSimpleIntent

class ChoosingPlace : State() {

    override fun handleInternal(request: AliceBotRequest, alice: AliceReactions): State {
        return if (request.hasSimpleIntent(SimpleIntent.KREMLIN) || request.input == ("вокруг кремля")) {
            kremlin(request, alice)
        } else if (request.hasSimpleIntent(SimpleIntent.MY_CHOICE) || request.input == ("в парке")) {
            alice.say("Скажите \"Готов\", и мы начнём тренировку!")
            alice.buttons("готов!")
            GettingToStartPlace(kremlin = false)
        } else {
            // todo: fallback
            alice.say("Не поняла Вас. Ответьте еще разок, пожалуйста")
            alice.buttons(
                "Вокруг Кремля", "Своё место"
            )
            this
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
        return GettingToStartPlace(kremlin = true)
    }
}