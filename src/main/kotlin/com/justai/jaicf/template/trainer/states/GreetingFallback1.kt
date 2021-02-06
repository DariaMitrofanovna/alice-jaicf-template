package com.justai.jaicf.template.trainer.states

import com.justai.jaicf.channel.yandexalice.api.AliceBotRequest
import com.justai.jaicf.channel.yandexalice.AliceReactions

class GreetingFallback1 : State() {

    override fun handleInternal(request: AliceBotRequest, alice: AliceReactions): State {

        alice.say("Не до конца Вас поняла. Готовы начать тренировку?")
        alice.buttons("да")
        return ApprovingStart1()
    }
}