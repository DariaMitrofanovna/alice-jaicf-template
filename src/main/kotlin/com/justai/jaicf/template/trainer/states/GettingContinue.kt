package com.justai.jaicf.template.trainer.states

import com.justai.jaicf.channel.yandexalice.AliceReactions
import com.justai.jaicf.channel.yandexalice.api.AliceBotRequest
import com.justai.jaicf.channel.yandexalice.api.model.Image
import com.justai.jaicf.template.trainer.common_models.GeoPoint
import com.justai.jaicf.template.trainer.common_models.RandomPhrasesRepository
import com.justai.jaicf.template.trainer.excercises.ExcerciseRepository
import com.justai.jaicf.template.util.intent.SimpleIntent
import com.justai.jaicf.template.util.intent.hasSimpleIntent

class GettingContinue(
    private val prevRunning: Running,
    private val path: List<GeoPoint?>
) : State() {
    override val fallbackTexts: List<String> = listOf(
        "${RandomPhrasesRepository.notUnderstand.random} Продолжаем тренировку?",
        "Чтобы закончить тренировку, скажите \"Конец\", чтобы продолжить скажите \"Продолжаем\""
    )
    override val fallbackButtons: List<List<String>> = listOf(
        listOf("Продолжить", "Конец"),
        listOf("Продолжить", "Конец"),
        listOf("Сначала")
    )

    override fun handleInternal(request: AliceBotRequest, alice: AliceReactions): State {

        return when {

            // continue
            request.hasSimpleIntent(SimpleIntent.CONTINUE, SimpleIntent.YANDEX_CONFIRM) -> {
                val nextExcercise =
                    ExcerciseRepository.getNextRandomExcercise(
                        prevRunning.excerciseHistory,
                        kremlin = false,
                        level = prevRunning.level
                    )
                val excerciseRandomTitle = nextExcercise.genRandomTitle(prevRunning.hard)
                alice.say(
                    """
                        Тогда переходим к упражнениям! $excerciseRandomTitle 
                    """.trimIndent()
                )

                alice.image(
                    Image(
                        nextExcercise.imageId,
                        title = excerciseRandomTitle,
                        description = "Дальше будет бег, стоп-команда \"Олег\""
                    )
                )
                alice.say(
                    "музыка",
                    tts = nextExcercise.music(hard = prevRunning.hard)
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
                        Супер, завершаю тренировку.
                        Рассказать Вам о результатах?
                    """.trimIndent()
                )
                alice.buttons("Да!", "Не хочу")
                NeedResults(path)
            }

            else -> fallback(request, alice)
        }
    }
}