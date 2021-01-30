package com.justai.jaicf.template.trainer.states

import com.justai.jaicf.api.BotRequest
import com.justai.jaicf.channel.yandexalice.AliceReactions
import com.justai.jaicf.template.util.intent.IntentType

class ChoosingPlace : State() {

    override fun handleInternal(request: BotRequest, alice: AliceReactions): State {
        if (intentUtil.isIntentPresent(request, IntentType.KREMLIN) == true || request.input == ("вокруг кремля")) {
            alice.say("Кремль - чуть пойзже. Пока что есть только парк")
            alice.buttons("готов!")
            return TrainingStart()
        } else if (intentUtil.isIntentPresent(request, IntentType.MY_CHOICE) == true || request.input == ("в парке")) {
            alice.say("Скажите \"Готов\", и мы начнём тренировку!")
            alice.buttons("готов!")
            return TrainingStart()
        } else alice.say("Не поняла Вас. Ответьте еще разок, пожалуйста")
        alice.buttons(
            "Вокруг Кремля", "В парке"
        )
        return this
//            if (userUtil.hasGeoLocation(request.clientId)) {
//                alice.say("Когда будете на точке старта, скажите \"Готов!\", и мы начнем тренировку.")
//                return TrainingStart()
//            } else {
//                if (userUtil.isBesideStart(request.clientId)) {
//                    alice.say("Вы на точке старта. Скажите \"Готов!\", и мы начнем тренировку!")
//                    return TrainingStart()
//                } else {
//                    alice.say("Вы далеко от точки старта. Сначала нужно до нее добраться! Можете воспользоваться маршрутом до нужной точки  или пойти своим путем. Скажите \"Готов!\", и мы начнем тренировку.")
//                return CheckingLocation()
//                }
//            }

    }
}