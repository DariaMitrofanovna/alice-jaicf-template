package com.justai.jaicf.template.trainer.states

import com.justai.jaicf.api.BotRequest
import com.justai.jaicf.channel.yandexalice.AliceReactions
import com.justai.jaicf.template.trainer.Excercise
import com.justai.jaicf.template.trainer.RandomPhrase

class Training(private val level: Int, private val startTime: Long, val excercise: Excercise) : State() {

    override fun handleInternal(request: BotRequest, alice: AliceReactions): State {
        val time = (System.currentTimeMillis() - startTime) / 1000
        alice.say(
            RandomPhrase(
                "Круто, справились за $time секунд. Побежали дальше! Еще минута бега! Скажи стоп, когда закончишь.",
                "А вы спортсмен! Всего $time секунд. Еще минута бега! Скажи стоп, когда закончишь.",
                "$time секунд на упражнение, отлично, в путь! Еще минута бега! Скажи стоп, когда закончишь."
            ).random
        )
        alice.buttons(
            "Еще упражнений",
            "Я устал, закончить тренировку!"
            //"Хорош!",
            //"Довольно!"
        )

        return Running(level = level + 1, prevExcercise = excercise)
    }
}