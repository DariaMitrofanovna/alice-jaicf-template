package com.justai.jaicf.template.trainer.states

import com.justai.jaicf.channel.yandexalice.AliceReactions
import com.justai.jaicf.channel.yandexalice.api.AliceBotRequest
import com.justai.jaicf.channel.yandexalice.api.model.Button

class GettingToStartPlace(private val kremlin: Boolean) : State() {

    override fun handleInternal(request: AliceBotRequest, alice: AliceReactions): State {
        // todo: simple intent on_the_start

        return if (kremlin) {
            // todo: check geo
            alice.say("Вам полегче или посложнее?")
            alice.buttons("Полегче", "Посложнее")
            TrainingStart(chosenDuration = null)
        } else {
            alice.say("Сколько по времени будем тренить?")
            alice.buttons(
                "30 минут", "15 минут",
                "AGON" // fixme
            )
            return GettingDuration()
        }

    }
}