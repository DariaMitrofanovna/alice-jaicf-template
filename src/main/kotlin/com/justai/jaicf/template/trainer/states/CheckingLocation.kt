package com.justai.jaicf.template.trainer.states

import com.justai.jaicf.channel.yandexalice.api.AliceBotRequest
import com.justai.jaicf.channel.yandexalice.AliceReactions
import com.justai.jaicf.template.util.intent.IntentType

class CheckingLocation : State() {

    override fun handleInternal(request: AliceBotRequest, alice: AliceReactions): State {
        return if (intentUtil.isIntentPresent(request, IntentType.READY) == true) {
            alice.say("Вы на точке старта. Скажите \"Готов!\", и мы начнем тренировку!")
            TrainingStart()
        } else {
            alice.say("Доберитесь до точки, скажите \"Готов!\", и мы начнем тренировку.")
            this;
        }
    }


}
