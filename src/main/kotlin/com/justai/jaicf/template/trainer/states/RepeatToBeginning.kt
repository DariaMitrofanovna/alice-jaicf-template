package com.justai.jaicf.template.trainer.states

import com.justai.jaicf.channel.yandexalice.AliceReactions
import com.justai.jaicf.channel.yandexalice.api.AliceBotRequest
import com.justai.jaicf.channel.yandexalice.api.model.Image
import com.justai.jaicf.template.res.Images
import com.justai.jaicf.template.util.intent.SimpleIntent
import com.justai.jaicf.template.util.intent.hasSimpleIntent

class RepeatToBeginning : State() {

    override val fallbackTexts: List<String> = listOf("", "", "")

    override fun handleInternal(request: AliceBotRequest, alice: AliceReactions): State {
        return if (
            request.hasSimpleIntent(
                SimpleIntent.YANDEX_CONFIRM,
                SimpleIntent.OLEG,
                SimpleIntent.BEGINNIG
            )
        ) {
            InitialState().handleInternal(request, alice)
        } else {
            alice.say("Когда захотите потренироваться, зовите меня.")
            alice.buttons("Олег!")
            alice.endSession()
            this
        }
    }
}