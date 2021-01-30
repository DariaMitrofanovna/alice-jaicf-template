package com.justai.jaicf.template.trainer.states

import com.justai.jaicf.api.BotRequest
import com.justai.jaicf.channel.yandexalice.AliceReactions

class GreetingFallback1 : State() {

    override fun handleInternal(request: BotRequest, alice: AliceReactions): State {

        alice.say("Не до конца Вас поняла. Готовы начать тренировку?")
        alice.buttons("да")
        return ApprovingStart1()
    }
}