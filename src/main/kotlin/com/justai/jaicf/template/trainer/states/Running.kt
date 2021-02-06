package com.justai.jaicf.template.trainer.states

import com.justai.jaicf.channel.yandexalice.api.AliceBotRequest
import com.justai.jaicf.channel.yandexalice.AliceReactions
import com.justai.jaicf.channel.yandexalice.api.model.Image
import com.justai.jaicf.channel.yandexalice.api.model.ImageGallery
import com.justai.jaicf.template.trainer.excercises.Excercise
import com.justai.jaicf.template.trainer.excercises.ExcerciseHistory
import com.justai.jaicf.template.trainer.excercises.ExcerciseRepository
import java.time.Duration
import java.time.LocalTime
import kotlin.time.ExperimentalTime

class Running(
    private val level: Int = 0,
    private val excerciseHistory: ExcerciseHistory = ExcerciseHistory.empty(),
    private val prevExcercise: Excercise? = null,
    private val trainingStartTime: LocalTime
) :
    State() {

    @ExperimentalTime
    override fun handleInternal(request: AliceBotRequest, alice: AliceReactions): State {
        // todo: to end
//        if (request.input.toLowerCase()
//                .contains(regex = Regex("хорош|устал|довольно")) || request.hasSimpleIntent(SimpleIntent.ENOUGH)
//        ) {
//            alice.image(
//                url = Images.sadEndUrl,
//                title = "Отдыхай"
//            )
//            return HappyEnd()
//        }

        // todo: if enot!
        // next excercise
        val nextExcercise = ExcerciseRepository.getNextRandomExcercise(excerciseHistory)
        val title = nextExcercise.genRandomTitle()

        val currentDuration = Duration.between(LocalTime.now(), trainingStartTime)

        alice.say(text = "C начала трени прошло ${currentDuration.seconds}. $title") // todo: saying duration normal

        val imageGallery: ImageGallery = alice.ImageGallery()
        nextExcercise.imageIds.forEach { a -> imageGallery.addImage(Image(a)) }

        alice.say(
            "музыка",
            tts = "<speaker audio=\"dialogs-upload/a80c89a2-d508-4008-9a33-6a8dc12e2895/0d7f80a5-815f-4b0b-9855-40fedaec04ab.opus\">"
        )

        alice.say("Опять бег, скажи Енот когда закончишь!")

        alice.buttons(
            "Закончил", "Енот!"
        )

        alice.endSession()

        return Running(
            level = level + 1,
            prevExcercise = nextExcercise,
            trainingStartTime = trainingStartTime,
            excerciseHistory = excerciseHistory + nextExcercise
        )
    }
}