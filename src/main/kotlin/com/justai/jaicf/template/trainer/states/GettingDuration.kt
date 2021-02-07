package com.justai.jaicf.template.trainer.states

import com.justai.jaicf.channel.yandexalice.AliceReactions
import com.justai.jaicf.channel.yandexalice.api.AliceBotRequest
import com.justai.jaicf.template.util.intent.durationIntent
import java.time.Duration

class GettingDuration : State() {
    override val fallbackTexts: List<String> = listOf(
            "Не до конца Вас понял. Как долго хотите позаниматься?",
            "Мне нужно знать, сколько будем бегать. Скажите, например, \"30 минут\"",
            "Извините, не понял Вас. Начнем с начала?")

    override fun handleInternal(request: AliceBotRequest, alice: AliceReactions): State {
        var duration = request.durationIntent()?.duration
        // fixme: remove duration hardcode
        return if (duration != null || request.input.equals("время")) {
            // fixme: remove text
            if (duration == null) {
                duration = Duration.ofSeconds(3)
            }
            alice.say("Прочитал длительность ${duration?.seconds} секунд. Хотите легкую или сложную тренировку?")
            alice.buttons("Легкую", "Сложную")
            TrainingStart(duration)
        } else if (request.request?.originalUtterance == "AGON") { // fixme: test
            val testDuration = Duration.ofSeconds(20)
            alice.say("Тестовая треня ${testDuration.seconds}. Вам полегче или посложнее?")
            alice.buttons("Полегче", "Посложнее")
            TrainingStart(testDuration)
        } else {
            fallback(request, alice)
            if (fallbackDepth == 3) {
                fallbackDepth = 0
                alice.buttons("заново")
                return End()
            }
            return this
        }
    }
}