package com.justai.jaicf.template.trainer.states

import com.justai.jaicf.channel.yandexalice.AliceReactions
import com.justai.jaicf.channel.yandexalice.api.AliceBotRequest
import com.justai.jaicf.channel.yandexalice.api.model.Image
import com.justai.jaicf.template.res.Images

class Final : State() {

    override val fallbackTexts: List<String> = listOf("", "", "")

    override fun handleInternal(request: AliceBotRequest, alice: AliceReactions): State {
        alice.image(
                Image(Images.happyEnd),
        )
        alice.say("Закончили, сразу не падай на землю, походи, восстанови дыхание. Молодец!")
        alice.endSession()
        return this
    }

}