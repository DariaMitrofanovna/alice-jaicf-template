package com.justai.jaicf.template.trainer.states

import com.justai.jaicf.channel.yandexalice.AliceReactions
import com.justai.jaicf.channel.yandexalice.api.AliceBotRequest
import com.justai.jaicf.template.util.intent.durationIntent
import java.time.Duration

class GettingDuration : State() {
    override fun handleInternal(request: AliceBotRequest, alice: AliceReactions): State {
        val duration = request.durationIntent()?.duration
        return if (duration != null) {
            // fixme: remove text
            alice.say("Прочитал длительность ${duration.seconds} секунд. Вам Полегче или посложнее?")
            alice.buttons("Полегче", "Посложнее")
            TrainingStart(duration)
        } else if (request.request?.originalUtterance == "AGON") { // fixme: test
            val testDuration = Duration.ofSeconds(20)
            alice.say("Тестовая треня ${testDuration.seconds}. Вам Полегче или посложнее?")
            alice.buttons("Полегче", "Посложнее")
            TrainingStart(testDuration)
        } else {
            // todo: normal fallback
            alice.say("Не поняла, скажи длительность!")
            alice.buttons("15 минут")
            this
        }
    }
}