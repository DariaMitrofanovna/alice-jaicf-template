package com.justai.jaicf.template.trainer.states

import com.justai.jaicf.api.BotRequest
import com.justai.jaicf.channel.yandexalice.AliceReactions
import com.justai.jaicf.template.util.intent.IntentType


class ApprovingStart2 : State() {

    override fun handleInternal(request: BotRequest, alice: AliceReactions): State {

        return if (intentUtil.isIntentPresent(request, IntentType.BEGINIG) == true || request.input == "заново") {
            return InitialState().handleInternal(request, alice)
        } else {
            SadEnd().handleInternal(request, alice)
        }
    }
}