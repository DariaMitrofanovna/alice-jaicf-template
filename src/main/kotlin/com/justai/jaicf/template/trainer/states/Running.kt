package com.justai.jaicf.template.trainer.states

import com.justai.jaicf.channel.yandexalice.api.AliceBotRequest
import com.justai.jaicf.channel.yandexalice.AliceReactions
import com.justai.jaicf.channel.yandexalice.api.model.Image
import com.justai.jaicf.channel.yandexalice.api.model.ImageGallery
import com.justai.jaicf.template.res.Images
import com.justai.jaicf.template.trainer.Excercise
import com.justai.jaicf.template.trainer.TrainingRepository
import com.justai.jaicf.template.util.intent.SimpleIntent
import com.justai.jaicf.template.util.intent.hasSimpleIntent

class Running(val level: Int = 0, val prevExcercise: Excercise? = null) : State() {

    override fun handleInternal(request: AliceBotRequest, alice: AliceReactions): State {
        // to end
        if (request.input.toLowerCase()
                .contains(regex = Regex("хорош|устал|довольно")) || request.hasSimpleIntent(SimpleIntent.ENOUGH)
        ) {
            alice.image(
                url = Images.sadEndUrl,
                title = "Отдыхай"
            )
            return HappyEnd()
        }

        // next excercise
        val nextExcercise = TrainingRepository.getRandomExcercise(prevExcercise?.type)
        val title = nextExcercise.genRandomTitle() + " Скажи когда закончишь."
        alice.say(text = title)
        val imageGallery: ImageGallery = alice.ImageGallery()
        nextExcercise.imageIds.forEach { a -> imageGallery.addImage(Image(a)) }
        alice.buttons(
            "Фух", "Всё!", "Закончил"
        )
        return Training(level, startTime = System.currentTimeMillis(), excercise = nextExcercise)

    }
}