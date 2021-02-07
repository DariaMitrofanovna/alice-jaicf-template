package com.justai.jaicf.template.trainer.states

import com.justai.jaicf.channel.yandexalice.AliceReactions
import com.justai.jaicf.channel.yandexalice.api.AliceBotRequest
import com.justai.jaicf.template.trainer.common_models.RandomPhrasesRepository
import com.justai.jaicf.template.util.intent.SimpleIntent
import com.justai.jaicf.template.util.intent.hasSimpleIntent

class GettingToStartPlace(private val kremlin: Boolean) : State() {
    override val fallbackTexts: List<String> = listOf(
        "${RandomPhrasesRepository.notUnderstand.random} Готовы начать сейчас?",
        "Чтобы начать, нужно сказать мне об этом. Например: \"Начинаем\""
    )

    override val fallbackButtons: List<List<String>> = listOf(
        listOf("Готов"),
        listOf("Начинаем"),
        listOf("Сначала")
    )

    override fun handleInternal(request: AliceBotRequest, alice: AliceReactions): State {
        return when {
            kremlin -> {
                if (request.hasSimpleIntent(SimpleIntent.ON_THE_SPOT) || request.input == ("на месте")) {
                    // todo: check geo
                    alice.say("Хотите лёгкую или сложную тренировку?")
                    alice.buttons("Лёгкую", "Сложную")
                    TrainingStart(chosenDuration = null)
                } else {
                    fallback(request, alice)

                }
            }
            else -> {
                if (request.hasSimpleIntent(SimpleIntent.ON_THE_SPOT) || request.input == ("на месте")) {
                    alice.say("Сколько по времени хотели бы тренить?")
                    alice.buttons(
                        "30 минут", "15 минут",
                        "AGON" // fixme
                    )
                    return GettingDuration()
                } else fallback(request, alice)
            }
        }
    }
}


