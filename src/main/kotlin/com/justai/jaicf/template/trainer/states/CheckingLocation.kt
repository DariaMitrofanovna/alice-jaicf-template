package com.justai.jaicf.template.trainer.states

import com.justai.jaicf.api.BotRequest
import com.justai.jaicf.channel.yandexalice.AliceReactions
import com.justai.jaicf.template.util.intent.IntentType

class CheckingLocation : State() {

    override fun handleInternal(request: BotRequest, alice: AliceReactions): State {
        return if (intentUtil.isIntentPresent(request, IntentType.READY) == true) {
            alice.say("Вы на точке старта. Скажите \"Готов!\", и мы начнем тренировку!")
            TrainingStart()
        } else {
            alice.say("Доберитесь до точки, скажите \"Готов!\", и мы начнем тренировку.")
            this;
        }
    }


}
