package com.justai.jaicf.template.trainer.states

import com.justai.jaicf.api.BotRequest
import com.justai.jaicf.channel.yandexalice.AliceReactions

class TrainingStart : State() {

    override fun handleInternal(request: BotRequest, alice: AliceReactions): State {
        alice.say("Побежали!", tts = "Побежали!")
        alice.buttons("Стоп, давай упражнения")

        return Running()
    }
}