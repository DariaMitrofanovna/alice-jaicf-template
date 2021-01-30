package com.justai.jaicf.template.trainer.states

import com.justai.jaicf.api.BotRequest
import com.justai.jaicf.channel.yandexalice.AliceReactions
import com.justai.jaicf.template.util.intent.IntentType

class TrainingStart : State() {

    override fun handleInternal(request: BotRequest, alice: AliceReactions): State {
        return if(intentUtil.isIntentPresent(request, IntentType.READY) == true || request.input == "да") {
            alice.say("Побежали!", tts = "Побежали!")
            alice.buttons("Стоп, давай упражнения")

            Running()
        } else GreetingFallback1().handleInternal(request, alice);
    }
}