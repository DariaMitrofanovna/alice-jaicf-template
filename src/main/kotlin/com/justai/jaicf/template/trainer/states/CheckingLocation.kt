package com.justai.jaicf.template.trainer.states

import com.justai.jaicf.channel.yandexalice.AliceReactions
import com.justai.jaicf.channel.yandexalice.api.AliceBotRequest
import com.justai.jaicf.template.util.intent.SimpleIntent
import com.justai.jaicf.template.util.intent.hasSimpleIntent
import java.time.Duration

class CheckingLocation : State() {
    override val fallbackTexts: List<String> = listOf("", "", "")
    override fun handleInternal(request: AliceBotRequest, alice: AliceReactions): State {
        return if (request.hasSimpleIntent(SimpleIntent.READY)) {
            alice.say("Вы на точке старта. Скажите \"Готов!\", и мы начнем тренировку!")
            TrainingStart(Duration.ofMinutes(1))
        } else {
            alice.say("Доберитесь до точки, скажите \"Готов!\", и мы начнем тренировку.")
            this;
        }
    }


}
