package com.justai.jaicf.template.trainer.states

import com.justai.jaicf.channel.yandexalice.AliceReactions
import com.justai.jaicf.channel.yandexalice.api.AliceBotRequest
import com.justai.jaicf.template.trainer.common_models.RandomPhrasesRepository
import com.justai.jaicf.template.util.intent.durationIntent
import java.time.Duration

class GettingDuration : State() {
    override val fallbackTexts: List<String> = listOf(
            "${RandomPhrasesRepository.notUnderstand.random} Как долго хотите позаниматься?",
            "Мне нужно знать, сколько будем бегать. Скажите, например, \"30 минут\"")

    override val fallbackButtons: List<List<String>> = listOf(
            listOf("30 минут", "15 минут"),
            listOf("30 минут", "15 минут"),
            listOf("Сначала")
    )

    override fun handleInternal(request: AliceBotRequest, alice: AliceReactions): State {
        val duration = request.durationIntent()?.duration
        return when {
            (duration != null) -> {
//            if (duration == null) {
//                duration = Duration.ofSeconds(3)
//            }
                alice.say("Прочитал длительность ${duration?.seconds} секунд. Хотите легкую или сложную тренировку?")
                alice.buttons("Легкую", "Сложную")
                TrainingStart(duration)

            }
            (request.request?.originalUtterance == "AGON") -> { // fixme: test
                val testDuration = Duration.ofSeconds(20)
                alice.say("Тестовая треня ${testDuration.seconds}. Вам полегче или посложнее?")
                alice.buttons("Полегче", "Посложнее")
                TrainingStart(testDuration)
            }
            else -> fallback(request, alice)

        }
    }
}