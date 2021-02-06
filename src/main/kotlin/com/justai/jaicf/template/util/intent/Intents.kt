package com.justai.jaicf.template.util.intent

import com.justai.jaicf.channel.yandexalice.api.AliceBotRequest
import com.justai.jaicf.channel.yandexalice.api.Request

enum class SimpleIntent(val id: String) {
    YES("yes"),
    NO("no"),
    KREMLIN("kremlin"),
    MY_CHOICE("myChoice"),
    READY("ready"),
    START("start"),
    BEGINIG("begining"),

    OVERCOME("overcome"),
    ENOUGH("enough"),
}

fun AliceBotRequest.intent(id: String): Request.Nlu.Intent? {
    return request?.nlu?.intents?.get(id)
}

fun AliceBotRequest.hasSimpleIntent(type: SimpleIntent): Boolean {
    return intent(type.id) != null
}