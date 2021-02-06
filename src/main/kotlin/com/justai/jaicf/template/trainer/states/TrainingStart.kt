package com.justai.jaicf.template.trainer.states

import com.justai.jaicf.channel.yandexalice.api.AliceBotRequest
import com.justai.jaicf.channel.yandexalice.AliceReactions
import com.justai.jaicf.template.util.intent.SimpleIntent
import com.justai.jaicf.template.util.intent.hasSimpleIntent

class TrainingStart : State() {

    override fun handleInternal(request: AliceBotRequest, alice: AliceReactions): State {
        return if (request.hasSimpleIntent(SimpleIntent.START) || request.input == "да" || request.input == "готов" || request.input == "начинаем") {
            alice.say("Беги примерно минуту, потом скажи, что закончил. На старт, внимание, марш!")
            alice.buttons("Стоп, давай упражнения")

            Running()
        } else GreetingFallback1().handleInternal(request, alice);
    }
}