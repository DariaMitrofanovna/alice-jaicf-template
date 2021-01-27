package com.justai.jaicf.template.trainer.states

import com.justai.jaicf.api.BotRequest
import com.justai.jaicf.channel.yandexalice.AliceReactions
import com.justai.jaicf.channel.yandexalice.api.model.Image
import com.justai.jaicf.template.res.Images

class Running(val level: Int = 0) : State() {

    override fun handleInternal(request: BotRequest, alice: AliceReactions): State {
        if (request.input.toLowerCase().contains(regex = Regex("хорош|устал|довольно"))) {
            alice.image(
                url = Images.endUrl,
                title = "Отдыхай"
            )
            return End()
        } else {
//            alice.say("С вас 30 приседаний, поехали!")
//            alice.image(
//                Image(
//                    imageId = Images.sitdown,
//                    title = "С вас 30 приседаний, поехали!"
//                )
//            )
            alice.image(
                url = Images.sitdownUrl,
                title = "С вас 30 приседаний, поехали!"
            )
            alice.buttons(
                "Фух", "Всё!", "Закончил"
            )
            return Training(level, startTime = System.currentTimeMillis())
        }
    }
}