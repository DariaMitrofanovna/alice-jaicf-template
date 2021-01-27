package com.justai.jaicf.template.trainer.states

import com.justai.jaicf.api.BotRequest
import com.justai.jaicf.channel.yandexalice.AliceReactions

class ChoosingPlace : State() {

    override fun handleInternal(request: BotRequest, alice: AliceReactions): State {
        alice.say("Командуйте 'На старт, внимание, марш', и мы начнём!")
        alice.buttons("На старт, внимание, марш!")

        return TrainingStart()
    }
}