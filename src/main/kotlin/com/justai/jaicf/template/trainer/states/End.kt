package com.justai.jaicf.template.trainer.states

import com.justai.jaicf.channel.yandexalice.AliceReactions
import com.justai.jaicf.channel.yandexalice.api.AliceBotRequest
import com.justai.jaicf.template.res.Images

class End : State() {
    override val fallbackTexts: List<String> = listOf("", "", "")
    override fun handleInternal(request: AliceBotRequest, alice: AliceReactions): State {
        if (request.input.equals("заново")) {
            return InitialState().handleInternal(request, alice)
        } else
            alice.image(
                    url = Images.happyEndUrl,
                    title = "До скорого!"
            )
        return this
    }
}