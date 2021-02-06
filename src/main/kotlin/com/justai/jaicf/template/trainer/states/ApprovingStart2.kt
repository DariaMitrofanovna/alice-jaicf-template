package com.justai.jaicf.template.trainer.states

import com.justai.jaicf.channel.yandexalice.api.AliceBotRequest
import com.justai.jaicf.channel.yandexalice.AliceReactions
import com.justai.jaicf.template.util.intent.SimpleIntent
import com.justai.jaicf.template.util.intent.hasSimpleIntent


class ApprovingStart2 : State() {

    override fun handleInternal(request: AliceBotRequest, alice: AliceReactions): State {

        return if (request.hasSimpleIntent(SimpleIntent.START) || request.input == "начинаем" || request.input == "готов") {
            TrainingStart().handleInternal(request, alice)
        } else {
            GreetingFallback3().handleInternal(request, alice)
        }
    }
}