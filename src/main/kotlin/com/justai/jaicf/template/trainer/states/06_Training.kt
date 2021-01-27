package com.justai.jaicf.template.trainer.states

import com.justai.jaicf.api.BotRequest
import com.justai.jaicf.channel.yandexalice.AliceReactions

class Training(private val level: Int, private val startTime: Long) : State() {

    override fun handleInternal(request: BotRequest, alice: AliceReactions): State {
        val time = (System.currentTimeMillis() - startTime) / 1000
        alice.say("Круто, справились за $time секунд. Побежали дальше!")
        alice.buttons(
            "Хорош!", "Я устал", "Довольно!", "Еще упражнений"
        )

        return Running(level = level + 1)
    }
}