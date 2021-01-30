package com.justai.jaicf.template.trainer.states

import com.justai.jaicf.api.BotRequest
import com.justai.jaicf.channel.yandexalice.AliceReactions
import com.justai.jaicf.channel.yandexalice.api.model.Button
import com.justai.jaicf.template.res.Images
import com.justai.jaicf.template.trainer.Excercise
import com.justai.jaicf.template.trainer.TrainingRepository

class Running(val level: Int = 0, val prevExcercise: Excercise? = null) : State() {

    override fun handleInternal(request: BotRequest, alice: AliceReactions): State {
        // to end
        if (request.input.toLowerCase().contains(regex = Regex("хорош|устал|довольно"))) {
            alice.image(
                url = Images.sadEndUrl,
                title = "Отдыхай"
            )
            return HappyEnd()
        }

        // next excercise
        val nextExcercise = TrainingRepository.getRandomExcercise(prevExcercise?.type)
        val title = nextExcercise.genRandomTitle()

        alice.say(text = title)
        alice.image(
            title = title,
            url = nextExcercise.imageUrl
        )
        alice.buttons(
            "Фух", "Всё!", "Закончил"
        )
        return Training(level, startTime = System.currentTimeMillis(), excercise = nextExcercise)

    }
}