package com.justai.jaicf.template.trainer.states

import com.justai.jaicf.channel.yandexalice.api.AliceBotRequest
import com.justai.jaicf.channel.yandexalice.AliceReactions
import java.time.Duration
import java.time.LocalTime

class TrainingStart(private val chosenDuration: Duration) : State() {

    override fun handleInternal(request: AliceBotRequest, alice: AliceReactions): State {
        // todo: make intent training intesity

        alice.say("Беги примерно минуту ... (описание про бег), потом скажи Енот (кодовое слово). На старт, внимание, марш!")
        alice.buttons("Олег!")
        alice.endSession()

        val startTime = LocalTime.now()
        // todo: start time

        return Running(trainingStartTime = startTime, chosenDuration = chosenDuration)

        // todo: fallbacks

//            GreetingFallback1().handleInternal(request, alice);
    }
}