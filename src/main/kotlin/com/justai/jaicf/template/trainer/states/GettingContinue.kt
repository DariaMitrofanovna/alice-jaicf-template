package com.justai.jaicf.template.trainer.states

import com.justai.jaicf.channel.yandexalice.AliceReactions
import com.justai.jaicf.channel.yandexalice.api.AliceBotRequest
import com.justai.jaicf.template.trainer.excercises.ExcerciseRepository
import com.justai.jaicf.template.util.intent.SimpleIntent
import com.justai.jaicf.template.util.intent.hasSimpleIntent

class GettingContinue(private val prevRunning: Running) : State() {
    override fun handleInternal(request: AliceBotRequest, alice: AliceReactions): State {
        println("agon: ${request.request?.command}")
        return when {
            request.hasSimpleIntent(SimpleIntent.CONTINUE, SimpleIntent.YANDEX_CONFIRM) -> {
                val nextExcercise = ExcerciseRepository.getNextRandomExcercise(prevRunning.excerciseHistory)
                val excerciseRandomTitle = nextExcercise.genRandomTitle()
                alice.say(
                    """
                        Тогда переходим к упражнениям! $excerciseRandomTitle 
                    """.trimIndent()
                )

                alice.image(nextExcercise.image)
                alice.say(
                    "музыка",
                    tts = "<speaker audio=\"dialogs-upload/a80c89a2-d508-4008-9a33-6a8dc12e2895/f04431f0-a902-473d-8346-19a84fb0c3db.opus\">"
                )

                alice.say(
                    """
                    Отлично! Продолжаем бег. Я жду от Вас команду "Олег" для перехода к упражнениям
                """.trimIndent()
                )

                alice.buttons(
                    "Олег!"
                )
                alice.endSession()
                prevRunning.continueRunning(nextExcercise)
            }

            request.hasSimpleIntent(SimpleIntent.ENOUGH, SimpleIntent.YANDEX_REJECT) -> {
                alice.say(
                    """
                        Закончили, сразу не падай на землю, походи, восстанови дыхание.
                        Ты пробежал 100500км! (посчитали по геолокации)
                    """.trimIndent()
                )
                return HappyEnd()
            }

            else -> fallback(request, alice)
        }
    }
}