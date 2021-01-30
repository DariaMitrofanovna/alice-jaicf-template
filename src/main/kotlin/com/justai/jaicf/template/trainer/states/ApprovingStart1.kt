package com.justai.jaicf.template.trainer.states

import com.justai.jaicf.api.BotRequest
import com.justai.jaicf.channel.yandexalice.AliceReactions
import com.justai.jaicf.template.util.intent.IntentType


class ApprovingStart1 : State() {

    override fun handleInternal(request: BotRequest, alice: AliceReactions): State {

        return if (intentUtil.isIntentPresent(request, IntentType.START) == true || request.input == "да" || request.input == "готов") {
            TrainingStart().handleInternal(request, alice)
        } else {
            GreetingFallback2().handleInternal(request, alice)
        }
    }
}