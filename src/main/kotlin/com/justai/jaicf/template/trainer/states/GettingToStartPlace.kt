package com.justai.jaicf.template.trainer.states

import com.justai.jaicf.channel.yandexalice.AliceReactions
import com.justai.jaicf.channel.yandexalice.api.AliceBotRequest
import com.justai.jaicf.channel.yandexalice.api.model.Button

class GettingToStartPlace : State() {

    override fun handleInternal(request: AliceBotRequest, alice: AliceReactions): State {
        // todo: simple intent on_the_start
        alice.say("Сколько по времени будем тренить?")
        alice.buttons(
            "30 минут", "15 минут"
        )
        return GettingDuration()
    }
}