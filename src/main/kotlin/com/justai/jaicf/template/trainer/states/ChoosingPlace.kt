package com.justai.jaicf.template.trainer.states

import com.justai.jaicf.channel.yandexalice.AliceReactions
import com.justai.jaicf.channel.yandexalice.api.AliceBotRequest
import com.justai.jaicf.template.trainer.common_models.RandomPhrasesRepository
import com.justai.jaicf.template.util.intent.SimpleIntent
import com.justai.jaicf.template.util.intent.hasSimpleIntent

class ChoosingPlace : State() {
    override val fallbackTexts: List<String> = listOf(
            "${RandomPhrasesRepository.notUnderstand.random} Где будете тренироваться?",
            "Чтобы потренироваться, нужно выбрать где. Скажите Кремль или свое место.",
    )
    override val fallbackButtons: List<List<String>> = listOf(
            listOf("Кремль", "свое место"),
            listOf("Кремль", "свое место"),
            listOf("Сначала")
    )

    override fun handleInternal(request: AliceBotRequest, alice: AliceReactions): State {
        return when {
            request.hasSimpleIntent(SimpleIntent.KREMLIN) -> {
                kremlin(request, alice)
            }
            (request.hasSimpleIntent(SimpleIntent.MY_CHOICE)) -> {
                alice.say("Скажите, когда будете на месте")
                alice.buttons("на месте!")
                GettingToStartPlace(kremlin = false)
            }
            else -> fallback(request, alice)
        }
    }

    private fun kremlin(request: AliceBotRequest, alice: AliceReactions): State {
        alice.say(
                """
                Когда будете на точке старта, скажите "я на месте", и мы начнем тренировку.
            """.trimIndent()
        )
        alice.buttons("Я на месте")
        alice.endSession()
        return GettingToStartPlace(kremlin = true)
    }
}

