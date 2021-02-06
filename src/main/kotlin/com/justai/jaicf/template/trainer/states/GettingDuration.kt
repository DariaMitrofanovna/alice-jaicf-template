package com.justai.jaicf.template.trainer.states

import com.justai.jaicf.channel.yandexalice.AliceReactions
import com.justai.jaicf.channel.yandexalice.api.AliceBotRequest
import com.justai.jaicf.template.util.intent.durationIntent
import kotlin.time.ExperimentalTime

class GettingDuration : State() {
    @ExperimentalTime
    override fun handleInternal(request: AliceBotRequest, alice: AliceReactions): State {
        val duration = request.durationIntent()?.duration
        if (duration != null) {
            // todo: save duration to state
            alice.say("Прочитал длительность $duration. Вам Полегче или посложнее?")
            alice.buttons("Полегче", "Посложнее")
            return TrainingStart()
        } else {
            // todo: normal fallback
            alice.say("Не поняла, скажи длительность!")
            alice.buttons("15 минут")
            return this
        }
    }
}