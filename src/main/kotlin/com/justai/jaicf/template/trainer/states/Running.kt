package com.justai.jaicf.template.trainer.states

import com.justai.jaicf.channel.yandexalice.api.AliceBotRequest
import com.justai.jaicf.channel.yandexalice.AliceReactions
import com.justai.jaicf.template.trainer.excercises.Excercise
import com.justai.jaicf.template.trainer.excercises.ExcerciseHistory
import com.justai.jaicf.template.trainer.excercises.ExcerciseRepository
import com.justai.jaicf.template.util.intent.SimpleIntent
import com.justai.jaicf.template.util.intent.hasSimpleIntent
import java.time.Duration
import java.time.LocalTime

class Running(
    private val level: Int = 0,
    val excerciseHistory: ExcerciseHistory = ExcerciseHistory.empty(),
    private val trainingStartTime: LocalTime,
    private val chosenDuration: Duration,
    private val overTime: Boolean = false
) : State() {

    private val MINIMUM_DURATION = Duration.ofMinutes(5)

    override fun handleInternal(request: AliceBotRequest, alice: AliceReactions): State {

        // todo: oleg intent here
        return if (request.hasSimpleIntent(SimpleIntent.OLEG)) {

//        }
//        return if (request.request?.command?.contains("олег", ignoreCase = true) == true) {
            oleg(request, alice)
        } else {
            fallback(request, alice)
        }
    }

    fun oleg(request: AliceBotRequest, alice: AliceReactions): State {
        val currentDuration = Duration.between(trainingStartTime, LocalTime.now())
        val final = currentDuration - chosenDuration < MINIMUM_DURATION && !overTime
        println("agon: chosen: $chosenDuration, current: $currentDuration, final: $final")

        val nextExcercise = ExcerciseRepository.getNextRandomExcercise(excerciseHistory)
        val excerciseRandomTitle = nextExcercise.genRandomTitle()

        // todo: saying duration normal
        // todo: do we need this?
//        alice.say(text = "C начала трени прошло ${currentDuration.seconds} секунд. $excerciseTitle")

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

        alice.image(nextExcercise.image)
        alice.say(
            "музыка",
            tts = "<speaker audio=\"dialogs-upload/a80c89a2-d508-4008-9a33-6a8dc12e2895/f04431f0-a902-473d-8346-19a84fb0c3db.opus\">"
        )

        if (final) {
            alice.say(
                """
                     Время, которое Вы хотели потренироваться, закончилось.
                     Можем завершить тренировку или еще позаниматься. Продолжаем?
                """.trimIndent()
            )
            alice.buttons("Продолжаем!", "Нет, закончить") // todo: texts
            return GettingContinue(this)
        } else {
            alice.say(
                """
                    Отлично! Продолжаем бег. Я жду от Вас команду "Олег" для перехода к упражнениям
                """.trimIndent()
            )
            alice.buttons(
                "Олег!"
            )
            alice.endSession()
            return Running(
                level = level + 1,
                trainingStartTime = trainingStartTime,
                excerciseHistory = excerciseHistory + nextExcercise,
                chosenDuration = chosenDuration,
                overTime = overTime
            )
        }
    }

    fun continueRunning(nextExcercise: Excercise): Running {
        return Running(
            level = level + 1,
            trainingStartTime = trainingStartTime,
            excerciseHistory = excerciseHistory + nextExcercise,
            chosenDuration = chosenDuration,
            overTime = true
        )
    }
}