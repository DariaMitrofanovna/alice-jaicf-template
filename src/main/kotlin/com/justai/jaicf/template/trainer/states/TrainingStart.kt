package com.justai.jaicf.template.trainer.states

import com.justai.jaicf.channel.yandexalice.AliceReactions
import com.justai.jaicf.channel.yandexalice.api.AliceBotRequest
import com.justai.jaicf.template.trainer.excercises.KremlinRoute
import com.justai.jaicf.template.util.intent.SimpleIntent
import com.justai.jaicf.template.util.intent.hasSimpleIntent
import java.time.Duration
import java.time.LocalTime

class TrainingStart(private val chosenDuration: Duration? = null) : State() {

    private val kremlin: Boolean = chosenDuration == null

    override val fallbackTexts: List<String> = listOf(
            "Не до конца Вас понял. Хотите легкую или сложную тренировку?",
            "Мне нужно знать, какая будет тренировка. Скажите, например: \"легкая\"",
            "Извините, не понял Вас. Начнем с начала?)")

    override fun handleInternal(request: AliceBotRequest, alice: AliceReactions): State {
        // todo: make intent training intesity, save intencity
        val hard = true
        if (request.hasSimpleIntent(SimpleIntent.DIFFICULTY_HARD) || request.input == ("сложную")
                || request.hasSimpleIntent(SimpleIntent.DIFFICULTY_LIGHT) || request.input == ("легкую")) {

            // todo: make intent training intesity, save intencity


            if (kremlin) {
                val nextPoint = KremlinRoute.points[0]
                alice.say( // todo: text
                        """
                    Отлично! Я  приготовил для Вас классную тренировку вокруг Кремля.
                    Сперва бегите до ${nextPoint.runToName} и скажите "Олег", когда добежите.
                    На старт, внимание, марш!
                """.trimIndent()
                )
            } else {
                alice.say(
                        """
                   Отлично! Начнем с небольшой пробежки. Бегите в своем темпе несколько минут.
                   Я уже приготовил для Вас классные упражнения. Скажите "Олег", когда будете готовы перейти к ним после бега.      
                """.trimIndent()
                )
                alice.buttons("Олег!")
            }
//            alice.endSession()

        val startTime = LocalTime.now()
        // todo: start time

        return Running(
            trainingStartTime = startTime,
            chosenDuration = chosenDuration,
            hard = hard
        )

            // todo: fallbacks
        } else fallback(request, alice)
        if (fallbackDepth == 3) {
            fallbackDepth = 0
            alice.buttons("заново")
            return End()
        }
        return this
    }
}


