package com.justai.jaicf.template.trainer.states

import com.justai.jaicf.api.BotRequest
import com.justai.jaicf.channel.yandexalice.AliceReactions
import com.justai.jaicf.template.util.intent.IntentType

class TrainingStart(val level: Int = 0) : State() {

    override fun handleInternal(request: BotRequest, alice: AliceReactions): State {

        return if (intentUtil.isIntentPresent(request, IntentType.START) == true || request.input == "готов" || request.input == "да") {
            alice.say("Отлично, сначала отрезок бега. Беги примерно 2 минуты. Скажи, когда остановишься. На старт, внимание, марш.")
            alice.buttons("всё")
            Training(level, startTime = System.currentTimeMillis())
        } else {
            GreetingFallback1().handleInternal(request, alice)
        }
    }
}