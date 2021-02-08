package com.justai.jaicf.template.trainer.states

import com.justai.jaicf.channel.yandexalice.AliceReactions
import com.justai.jaicf.channel.yandexalice.api.AliceBotRequest
import com.justai.jaicf.channel.yandexalice.api.model.Image
import com.justai.jaicf.template.trainer.common_models.GeoPoint
import com.justai.jaicf.template.trainer.common_models.RandomPhrasesRepository
import com.justai.jaicf.template.trainer.excercises.ExcerciseHistory
import com.justai.jaicf.template.trainer.excercises.ExcerciseRepository
import com.justai.jaicf.template.trainer.excercises.Excercise
import com.justai.jaicf.template.trainer.excercises.KremlinRoute
import com.justai.jaicf.template.util.intent.SimpleIntent
import com.justai.jaicf.template.util.intent.hasSimpleIntent
import java.time.Duration
import java.time.LocalTime

class Running(
        val level: Int = 0,
        val excerciseHistory: ExcerciseHistory = ExcerciseHistory.empty(),
        private val trainingStartTime: LocalTime,
        private val chosenDuration: Duration?,
        private val overTime: Boolean = false,
        val hard: Boolean,
        val path: List<GeoPoint?> = listOf()
) : State() {

    private val MINIMUM_DURATION = Duration.ofMinutes(3)

    private val kremlin: Boolean = chosenDuration == null

    override val fallbackTexts: List<String> = listOf(
            "${RandomPhrasesRepository.notUnderstand.random} Готовы к упражнениям?",
            "Если Вы закончили бегать, можем делать упражнения. Скажите \"Олег\"",
    )

    override val fallbackButtons: List<List<String>> = listOf(
            listOf("Да"),
            listOf("Олег!"),
            listOf("Сначала")
    )


    override fun handleInternal(request: AliceBotRequest, alice: AliceReactions): State {
        return when {
            (request.hasSimpleIntent(SimpleIntent.OLEG, SimpleIntent.YANDEX_CONFIRM)) -> {
                oleg(request, alice)
            }

            request.hasSimpleIntent(SimpleIntent.ENOUGH) -> {
                alice.say(
                    """
                        Хорошо, закончили.
                        Рассказать Вам о результатах?
                    """.trimIndent()
                )
                alice.buttons("Да!", "Не хочу")
                NeedResults(path)
            }

            request.hasSimpleIntent(SimpleIntent.YANDEX_REPEAT) -> {
                return oleg(request, alice, repeat = true)
            }

            else -> {
                fallback(request, alice)
            }
        }
    }

    fun oleg(request: AliceBotRequest, alice: AliceReactions, repeat: Boolean = false): State {
        val currentGeo = request.session.location?.let(GeoPoint::fromLocation)
        val newPath = if (!repeat) path + currentGeo else path

        val currentDuration = Duration.between(trainingStartTime, LocalTime.now())

        val final = if (kremlin) {
            level == KremlinRoute.finalLevel
        } else {
            chosenDuration!!
            (chosenDuration - currentDuration < MINIMUM_DURATION) && !overTime
        }

        println("agon: currentDuration: ${currentDuration.seconds}, chosen: ${chosenDuration?.seconds}, overTime: $overTime")

        val nextExcercise = if (!repeat) {
            ExcerciseRepository.getNextRandomExcercise(excerciseHistory, kremlin, level)
        } else {
            excerciseHistory.excercises.last()
        }
        val excerciseRandomTitle = nextExcercise.genRandomTitle(hard)

        if (final) {
            alice.say(
                    """
                    Осталось совсем чуть-чуть - это финальное упражнение на сегодня. $excerciseRandomTitle
                """.trimIndent()
            )
        } else {
            alice.say(
                    """
                    Переходим к упражнениям! $excerciseRandomTitle 
                """.trimIndent()
            )
        }

        val description = when {
            final && kremlin -> "Дальше могу расскзать о результатах"
            final && !kremlin -> "И время закончилось"
            kremlin -> {
                val nextPoint = KremlinRoute.points[level + 1]
                "Следующая точка - ${nextPoint.name}"
            }
            else -> "Дальше будет бег, для перехода к упражнениям, позовите \"Олега\" " +
                    if (overTime) "(а для завершения тренировки скажите, что устали)" else ""
        }

        alice.image(
                Image(
                        imageId = nextExcercise.imageId,
                        title = nextExcercise.title(hard),
                        description = description
                )
        )
        alice.say(
                "музыка",
                tts = nextExcercise.music(hard)
        )

        if (final) {
            return if (kremlin) {
                alice.say(
                    """
                        Ура! Тренировка закончена.
                        Рассказать Вам о результатах?
                    """.trimIndent()
                )
                alice.buttons("Да!", "Не хочу")
                NeedResults(newPath)
            } else {
                alice.say(
                        """
                     Время, которое Вы хотели потренироваться, закончилось.
                     Можем завершить тренировку или еще позаниматься. Продолжаем?
                """.trimIndent()
                )
                alice.buttons("Продолжаем!", "Заканчиваем")
                GettingContinue(this, newPath)
            }
        } else {
            if (kremlin) {
                val nextPoint = KremlinRoute.points[level + 1]
                alice.say(
                        """
                        Отлично! Продолжаем бег. Теперь бегите ${nextPoint.runToName}. 
                        Скажите "Олег", когда будете там!
                    """.trimIndent()
                )
            } else {
                alice.say(
                        """
                        Отлично! Продолжаем бег. 
                        Я жду от Вас команду "Олег" для перехода к упражнениям.  ${if (overTime) "В любой момент скажите, что устали, и мы закончим." else ""} 
                    """.trimIndent()
                )
            }

            alice.buttons(
                    "Олег!"
            )
            alice.endSession()
            return Running(
                    level = level + 1,
                    trainingStartTime = trainingStartTime,
                    excerciseHistory = excerciseHistory + nextExcercise,
                    chosenDuration = chosenDuration,
                    overTime = overTime,
                    hard = hard,
                    path = newPath
            )
        }
    }

    fun continueRunning(nextExcercise: Excercise, path: List<GeoPoint?>): Running {
        return Running(
                level = level + 1,
                trainingStartTime = trainingStartTime,
                excerciseHistory = excerciseHistory + nextExcercise,
                chosenDuration = chosenDuration,
                overTime = true,
                hard = hard,
                path = path
        )
    }
}