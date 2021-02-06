package com.justai.jaicf.template.trainer.states

import com.justai.jaicf.channel.yandexalice.api.AliceBotRequest
import com.justai.jaicf.channel.yandexalice.AliceReactions
import com.justai.jaicf.template.util.intent.IntentType

class TrainingStart : State() {

    override fun handleInternal(request: AliceBotRequest, alice: AliceReactions): State {
        return if(intentUtil.isIntentPresent(request, IntentType.START) == true || request.input == "да" || request.input == "готов" || request.input == "начинаем") {
            alice.say("Беги примерно минуту, потом скажи, что закончил. На старт, внимание, марш!")
            alice.buttons("Стоп, давай упражнения")

            Running()
        } else GreetingFallback1().handleInternal(request, alice);
    }
}