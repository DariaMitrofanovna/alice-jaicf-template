package com.justai.jaicf.template.trainer.states

import com.justai.jaicf.api.BotRequest
import com.justai.jaicf.channel.yandexalice.AliceReactions

class GreetingFallback3 : State() {

    override fun handleInternal(request: BotRequest, alice: AliceReactions): State {

        alice.say("Извините, не поняла Вас. Начнем с начала или в другой раз?")
        alice.buttons("Заново", "В другой раз")
        return ApprovingStart3()
    }
}