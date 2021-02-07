package com.justai.jaicf.template.trainer.states

import com.justai.jaicf.channel.yandexalice.AliceReactions
import com.justai.jaicf.channel.yandexalice.api.AliceBotRequest
import com.justai.jaicf.template.res.Images

class SadEnd : State() {
    override val fallbackTexts: List<String> = listOf("", "", "")
    override fun handleInternal(request: AliceBotRequest, alice: AliceReactions): State {
        alice.say("Грустно, что мы расстаёмся ((")
        alice.image(
            url = Images.sadEndUrl,
            title = "Sad end"
        )
        return this
    }
}