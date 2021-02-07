package com.justai.jaicf.template.trainer.states

import com.justai.jaicf.channel.yandexalice.AliceReactions
import com.justai.jaicf.channel.yandexalice.api.AliceBotRequest
import com.justai.jaicf.template.util.intent.SimpleIntent
import com.justai.jaicf.template.util.intent.hasSimpleIntent

class GettingToStartPlace(private val kremlin: Boolean) : State() {
    override val fallbackTexts: List<String> = listOf(
            "Не до конца Вас понял. Готовы начать сейчас?",
            "Чтобы начать, нужно сказать мне об этом. Например: \"Начинаем\"",
            "Извините, не понял Вас. Начнем с начала?")

    override fun handleInternal(request: AliceBotRequest, alice: AliceReactions): State {
        // todo: simple intent on_the_start

        return if (kremlin) {
            // todo: check geo
            alice.say("Вам полегче или посложнее?")
            alice.buttons("Полегче", "Посложнее")
            TrainingStart(chosenDuration = null)
        } else {
            if (request.hasSimpleIntent(SimpleIntent.ON_THE_SPOT) || request.input == ("на месте")) {
                alice.say("Сколько по времени хотели бы тренить?")
                alice.buttons(
                        "30 минут", "15 минут",
                        "AGON" // fixme
                )
                return GettingDuration()
            } else fallback(request, alice)
            if (fallbackDepth == 3) {
                fallbackDepth = 0
                alice.buttons("заново")
                return End()
            }
            return this
        }
    }
}


