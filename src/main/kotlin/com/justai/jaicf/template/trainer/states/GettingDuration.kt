package com.justai.jaicf.template.trainer.states

import com.justai.jaicf.channel.yandexalice.AliceReactions
import com.justai.jaicf.channel.yandexalice.api.AliceBotRequest
import com.justai.jaicf.template.trainer.common_models.RandomPhrasesRepository
import com.justai.jaicf.template.util.intent.durationIntent

class GettingDuration : State() {
    override val fallbackTexts: List<String> = listOf(
        "${RandomPhrasesRepository.notUnderstand.random} Как долго хотите позаниматься?",
        "Мне нужно знать, сколько будем бегать. Скажите, например, \"20 минут\""
    )

    override val fallbackButtons: List<List<String>> = listOf(
        listOf("30 минут", "15 минут"),
        listOf("30 минут", "15 минут"),
        listOf("Сначала")
    )

    override fun handleInternal(request: AliceBotRequest, alice: AliceReactions): State {
        val duration = request.durationIntent()?.duration
        return when {
            (duration != null) -> {
                alice.say(
                    "Понял Вас, это получается ... ${duration.seconds} секунд (люблю точность). Хотите легкую или сложную тренировку?",
                    "Понял Вас sil <[500]>, это получается sil <[1000]> ${duration.seconds} секунд (люблю точность). Хотите легкую или сложную тренировку?"
                )
                alice.buttons("Легкую", "Сложную")
                TrainingStart(duration)
            }
            else -> fallback(request, alice)
        }
    }
}