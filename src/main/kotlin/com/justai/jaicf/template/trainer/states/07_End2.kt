package com.justai.jaicf.template.trainer.states

import com.justai.jaicf.api.BotRequest
import com.justai.jaicf.channel.yandexalice.AliceReactions
import com.justai.jaicf.template.res.Images

class SadEnd : State() {
    override fun handleInternal(request: BotRequest, alice: AliceReactions): State {
        alice.image(
            url = Images.happyEndUrl,
            title = "Sad end"
        )
        return this
    }
}