package com.justai.jaicf.template.trainer.states

import com.justai.jaicf.channel.yandexalice.AliceReactions
import com.justai.jaicf.channel.yandexalice.api.AliceBotRequest
import com.justai.jaicf.template.trainer.excercises.Excercise
import com.justai.jaicf.template.trainer.excercises.ExcerciseHistory
import com.justai.jaicf.template.trainer.excercises.ExcerciseRepository
import com.justai.jaicf.template.trainer.excercises.KremlinRoute
import com.justai.jaicf.template.util.intent.SimpleIntent
import com.justai.jaicf.template.util.intent.hasSimpleIntent
import java.time.Duration
import java.time.LocalTime
import com.justai.jaicf.channel.yandexalice.api.model.Image
import com.justai.jaicf.template.trainer.common_models.GeoPoint
import com.justai.jaicf.template.trainer.common_models.RandomPhrasesRepository

class Running(
    private val level: Int = 0,
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
        "${RandomPhrasesRepository.notUnderstand.random} Закончим или начнем с начала?"
    )

    override val fallbackButtons: List<List<String>> = listOf(
        listOf("Да"),
        listOf("Олег!"),
        listOf("Сначала")
    )


    override fun handleInternal(request: AliceBotRequest, alice: AliceReactions): State {
        return if (request.hasSimpleIntent(SimpleIntent.OLEG, SimpleIntent.YANDEX_CONFIRM)) {
            oleg(request, alice)
        } else {
            fallback(request, alice)
        }
    }

    fun oleg(request: AliceBotRequest, alice: AliceReactions): State {
        val currentGeo = request.session.location?.let(GeoPoint::fromLocation)
        val newPath = path + currentGeo

        val currentDuration = Duration.between(trainingStartTime, LocalTime.now())

        val final = if (kremlin) {
            level == KremlinRoute.finalLevel
        } else {
            chosenDuration!!
            (chosenDuration - currentDuration < MINIMUM_DURATION) && !overTime
        }

        println("agon: currentDuration: ${currentDuration.seconds}, chosen: ${chosenDuration?.seconds}, overTime: $overTime")

        val nextExcercise = ExcerciseRepository.getNextRandomExcercise(excerciseHistory)
        val excerciseRandomTitle = nextExcercise.genRandomTitle(hard)

        if (final) {
            // todo: has been done 1 or more excercises?
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
            final -> ""
            kremlin -> {
                val nextPoint = KremlinRoute.points[level + 1]
                "Следующая точка - ${nextPoint.runToName}"
            }
            else -> "Дальше будет бег, стоп-команда \"Олег\""
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
            tts = "<speaker audio=\"dialogs-upload/a80c89a2-d508-4008-9a33-6a8dc12e2895/f04431f0-a902-473d-8346-19a84fb0c3db.opus\">"
        )

        if (final) {
            return if (kremlin) {
                // todo: distance
                alice.say( // fixme: text
                    """
                        Ура! Закончили.
                        Вы пробежали путь из ${newPath.size} точек!
                    """.trimIndent()
                )
                // todo: todo final suggests
                alice.endSession()
                HappyEnd()
            } else {
                alice.say(
                    """
                     Время, которое Вы хотели потренироваться, закончилось.
                     Можем завершить тренировку или еще позаниматься. Продолжаем?
                """.trimIndent()
                )
                alice.buttons("Продолжаем!", "Заканчиваем") // todo: texts
                GettingContinue(this, newPath)
            }
        } else {
            if (kremlin) {
                val nextPoint = KremlinRoute.points[level + 1]
                //alice.link("Следующая точка - ${nextPoint.runToName}", "https://ya.ru")
                alice.say(
                    """
                        Отлично! Продолжаем бег. Теперь бегите до ${nextPoint.runToName}. 
                        Скажите "Олег", когда будете там!
                    """.trimIndent()
                )
            } else {
                alice.say(
                    """
                        Отлично! Продолжаем бег. Я жду от Вас команду "Олег" для перехода к упражнениям
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