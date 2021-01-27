package com.justai.jaicf.template.trainer.states

import com.justai.jaicf.api.BotRequest
import com.justai.jaicf.channel.yandexalice.AliceReactions

class InitialState : State() {
    override fun handleInternal(request: BotRequest, alice: AliceReactions): State {
        alice.say(
            text = """
                Привет! Я тренер Боб (друг Алисы). Предлагаю устроить такую тренировку: бег, силовое упражнение, снова бег - и снова упражнение. Повторять будем столько, сколько захотите.
                Если дадите доступ к геолокации, я посчитаю итоговую дистанцию.
            """.trimIndent()
        )
        alice.buttons( // fixme: fake buttons instead of permission
            "Разрешить на 1 час",
            "Разрешить на 1 день",
            "Не разрешать"
        )

        return GettingGeoPermission()
    }
}