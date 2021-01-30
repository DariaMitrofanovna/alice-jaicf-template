package com.justai.jaicf.template.trainer.states

import com.justai.jaicf.api.BotRequest
import com.justai.jaicf.channel.yandexalice.AliceReactions
import com.justai.jaicf.template.res.Images

class SadEnd : State() {
    override fun handleInternal(request: BotRequest, alice: AliceReactions): State {
        alice.say("Грустно, что мы расстаёмся ((")
        alice.image(
            url = Images.sadEndUrl,
            title = "Sad end"
        )
        return this
    }
}