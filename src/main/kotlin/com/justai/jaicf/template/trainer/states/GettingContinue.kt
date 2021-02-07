package com.justai.jaicf.template.trainer.states

import com.justai.jaicf.channel.yandexalice.AliceReactions
import com.justai.jaicf.channel.yandexalice.api.AliceBotRequest
import com.justai.jaicf.template.trainer.common_models.GeoPoint
import com.justai.jaicf.template.trainer.excercises.ExcerciseRepository
import com.justai.jaicf.template.util.intent.SimpleIntent
import com.justai.jaicf.template.util.intent.hasSimpleIntent

class GettingContinue(
    private val prevRunning: Running,
    private val path: List<GeoPoint?>
) : State() {
    override fun handleInternal(request: AliceBotRequest, alice: AliceReactions): State {
        return when {

            // continue
            request.hasSimpleIntent(SimpleIntent.CONTINUE, SimpleIntent.YANDEX_CONFIRM) -> {
                val nextExcercise = ExcerciseRepository.getNextRandomExcercise(prevRunning.excerciseHistory)
                val excerciseRandomTitle = nextExcercise.genRandomTitle(prevRunning.hard)
                alice.say(
                    """
                        Тогда переходим к упражнениям! $excerciseRandomTitle 
                    """.trimIndent()
                )

                alice.image(nextExcercise.imageId)
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
                prevRunning.continueRunning(nextExcercise, path)
            }

            // not continue
            request.hasSimpleIntent(SimpleIntent.ENOUGH, SimpleIntent.YANDEX_REJECT) -> {
                alice.say(
                    """
                        Закончили, сразу не падай на землю, походи, восстанови дыхание.
                        Ты пробежал ${path.size} точек! (посчитали по геолокации)
                    """.trimIndent()
                )
                alice.endSession()
                return HappyEnd()
            }

            else -> fallback(request, alice)
        }
    }
}