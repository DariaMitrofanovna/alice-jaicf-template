package com.justai.jaicf.template.trainer.states

import com.justai.jaicf.api.BotRequest
import com.justai.jaicf.channel.yandexalice.AliceReactions

class GreetingFallback2 : State() {

    override fun handleInternal(request: BotRequest, alice: AliceReactions): State {

        alice.say("Чтобы начать тренировку, скажите мне, что Вы готовы. Например: \"Начинаем!\"")
        alice.buttons("Начинаем!")
        return ApprovingStart2()
    }
}