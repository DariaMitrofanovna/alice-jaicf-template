package com.justai.jaicf.template.trainer.states

import com.justai.jaicf.channel.yandexalice.api.AliceBotRequest
import com.justai.jaicf.channel.yandexalice.AliceReactions
import com.justai.jaicf.channel.yandexalice.api.model.Image
import com.justai.jaicf.template.res.Images

class InitialState : State() {
    override fun handleInternal(request: AliceBotRequest, alice: AliceReactions): State {
        alice.say(
            text = """
                Привет! Я тренер Боб. Предлагаю устроить такую тренировку: бег, силовое упражнение, снова бег - и снова упражнение. Повторять будем столько, сколько захотите.
                Если дадите доступ к геолокации, я посчитаю итоговую дистанцию.
            """.trimIndent()
        )
        alice.geoPermissionRequest()

        return GettingGeoPermission()
    }
}