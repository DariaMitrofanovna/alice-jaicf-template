package com.justai.jaicf.template.trainer.states

import com.justai.jaicf.channel.yandexalice.api.AliceBotRequest
import com.justai.jaicf.channel.yandexalice.AliceReactions
import com.justai.jaicf.template.trainer.excercises.Excercise
import com.justai.jaicf.template.trainer.excercises.ExcerciseHistory
import com.justai.jaicf.template.trainer.excercises.ExcerciseRepository
import com.justai.jaicf.template.util.intent.SimpleIntent
import com.justai.jaicf.template.util.intent.hasSimpleIntent
import java.time.Duration
import java.time.LocalTime

class Running(
    private val level: Int = 0,
    private val excerciseHistory: ExcerciseHistory = ExcerciseHistory.empty(),
    private val prevExcercise: Excercise? = null,
    private val trainingStartTime: LocalTime,
    private val chosenDuration: Duration
) :
    State() {

    override fun handleInternal(request: AliceBotRequest, alice: AliceReactions): State {

        // todo: oleg intent here
        return if (request.hasSimpleIntent(SimpleIntent.OLEG)) {

//        }
//        return if (request.request?.command?.contains("олег", ignoreCase = true) == true) {
            oleg(request, alice)
        } else {
            fallback(request, alice)
        }
    }

    fun oleg(request: AliceBotRequest, alice: AliceReactions): State {
        val currentDuration = Duration.between(trainingStartTime, LocalTime.now())
        println("agon: chosen: $chosenDuration, current: $currentDuration")
        if (currentDuration > chosenDuration) {
            // todo: final excercise
            alice.say("Закончили")
            return HappyEnd()
        }

        val nextExcercise = ExcerciseRepository.getNextRandomExcercise(excerciseHistory)
        val excerciseTitle = nextExcercise.genRandomTitle()

        // todo: do we need this?
        alice.say(text = "C начала трени прошло ${currentDuration.seconds} секунд. $excerciseTitle") // todo: saying duration normal

        // todo: one image
        alice.itemsList(header = excerciseTitle).also { items ->
//        alice.imageGallery(header = title).also { items ->
            nextExcercise.images.forEach { items.addImage(it) }
        }

        alice.say(
            "музыка",
            tts = "<speaker audio=\"dialogs-upload/a80c89a2-d508-4008-9a33-6a8dc12e2895/0d7f80a5-815f-4b0b-9855-40fedaec04ab.opus\">"
        )

        alice.say("Опять бег, скажи Олег когда закончишь!")

        alice.buttons(
            "Олег!"
        )

        alice.endSession()

        return Running(
            level = level + 1,
            prevExcercise = nextExcercise,
            trainingStartTime = trainingStartTime,
            excerciseHistory = excerciseHistory + nextExcercise,
            chosenDuration = chosenDuration
        )
    }
}