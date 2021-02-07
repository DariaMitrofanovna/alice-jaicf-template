package com.justai.jaicf.template.util.intent

import com.justai.jaicf.channel.yandexalice.api.AliceBotRequest
import com.justai.jaicf.channel.yandexalice.api.Request
import com.justai.jaicf.channel.yandexalice.model.AliceIntent

enum class SimpleIntent(val id: String) {
    YES("yes"),
    NO("no"),
    KREMLIN("kremlin"),
    MY_CHOICE("myChoice"),
    READY("ready"),
    START("start"),
    BEGINNIG("beginning"),
    OLEG("oleg"),
    CONTINUE("continue"),
    NOT_CONTINUE("not_continue"),
    ENOUGH("enough"),
    YANDEX_CONFIRM(AliceIntent.CONFIRM),
    YANDEX_REJECT(AliceIntent.REJECT),
    ON_THE_SPOT("onTheSpot"),
    DIFFICULTY_HARD("difficultyHard"),
    DIFFICULTY_LIGHT("difficultyLight")
}

fun AliceBotRequest.intent(id: String): Request.Nlu.Intent? {
    return request?.nlu?.intents?.get(id)
}

fun AliceBotRequest.hasSimpleIntent(vararg intents: SimpleIntent): Boolean {
    intents.forEach {
        if (intent(it.id) != null) {
            return true
        }
    }
    return false
}