package com.justai.jaicf.template.trainer.states

import com.justai.jaicf.channel.yandexalice.AliceReactions
import com.justai.jaicf.channel.yandexalice.api.AliceBotRequest
import com.justai.jaicf.template.trainer.common_models.RandomPhrasesRepository
import com.justai.jaicf.template.trainer.excercises.KremlinRoute
import com.justai.jaicf.template.util.intent.SimpleIntent
import com.justai.jaicf.template.util.intent.hasSimpleIntent
import java.time.Duration
import java.time.LocalTime

class TrainingStart(private val chosenDuration: Duration? = null) : State() {

    private val kremlin: Boolean = chosenDuration == null

    override val fallbackTexts: List<String> = listOf(
        "${RandomPhrasesRepository.notUnderstand.random} Хотите легкую или сложную тренировку?",
        "Мне нужно знать, какая будет тренировка. Скажите, например: \"легкая\""
    )

    override val fallbackButtons: List<List<String>> = listOf(
        listOf("Лёгкую", "Сложную"),
        listOf("Лёгкая", "Сложная")
    )

    override fun handleInternal(request: AliceBotRequest, alice: AliceReactions): State {
        return when {
            request.hasSimpleIntent(SimpleIntent.DIFFICULTY_HARD, SimpleIntent.DIFFICULTY_LIGHT) -> {
                val hard = request.hasSimpleIntent(SimpleIntent.DIFFICULTY_HARD)
                if (kremlin) {
                    val nextPoint = KremlinRoute.points[0]
                    alice.say(
                        """
                        Отлично! Я  приготовил для Вас классную тренировку вокруг Кремля.
                        Сперва бегите насквозь через Кремль, мимо памятника тесячелетию России, до ${nextPoint.runToName} и скажите "Олег", когда добежите.
                        На старт, внимание, марш!
                    """.trimIndent()
                    )
                    alice.buttons("Олег!")
                    alice.endSession()
                } else {
                    alice.say(
                        """
                        Отлично! Начнем с небольшой пробежки. Пробегитесь немного в своем темпе.
                        Я уже приготовил для Вас классные упражнения. Скажите "Олег", когда будете готовы перейти к ним после бега.      
                    """.trimIndent()
                    )
                    alice.buttons("Олег!")
                    alice.endSession()
                }

                val startTime = LocalTime.now()

                Running(
                    trainingStartTime = startTime,
                    chosenDuration = chosenDuration,
                    hard = hard
                )
            }

            else -> {
                fallback(request, alice)
            }
        }
    }
}


