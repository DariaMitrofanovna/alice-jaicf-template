package com.justai.jaicf.template.trainer.states

import com.justai.jaicf.channel.yandexalice.api.AliceBotRequest
import com.justai.jaicf.channel.yandexalice.AliceReactions
import com.justai.jaicf.template.trainer.excercises.KremlinRoute
import java.time.Duration
import java.time.LocalTime

class TrainingStart(private val chosenDuration: Duration? = null) : State() {

    private val kremlin: Boolean = chosenDuration == null

    override fun handleInternal(request: AliceBotRequest, alice: AliceReactions): State {
        // todo: make intent training intesity, save intencity
        val hard = true

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
                    Я уже приготовил для Вас классные упражнения. 
                    Скажите "Олег", когда будете готовы перейти к ним после бега.
                    На старт, внимание, марш!       
                """.trimIndent()
            )
        }

        alice.buttons("Олег!")
        alice.endSession()

        val startTime = LocalTime.now()
        // todo: start time

        return Running(
            trainingStartTime = startTime,
            chosenDuration = chosenDuration,
            hard = hard
        )

        // todo: fallbacks

//            GreetingFallback1().handleInternal(request, alice);
    }
}