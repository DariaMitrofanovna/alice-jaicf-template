package com.justai.jaicf.template.trainer.states

import com.justai.jaicf.channel.yandexalice.AliceReactions
import com.justai.jaicf.channel.yandexalice.api.AliceBotRequest

class InitialState : State() {

    override val fallbackTexts: List<String> = listOf("", "", "")

    override fun handleInternal(request: AliceBotRequest, alice: AliceReactions): State {
        alice.say(
            """
                Привет! Я тренер Олег. Предлагаю устроить такую тренировку: бег, силовое упражнение, снова бег - и снова упражнение.
                Если дадите доступ к геолокации, я посчитаю итоговую дистанцию.
            """.trimIndent()
        )
//        alice.geoPermissionRequest()
        alice.buttons("ok")

        return GettingGeoPermission()
    }
}