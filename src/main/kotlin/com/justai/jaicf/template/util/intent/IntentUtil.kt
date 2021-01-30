package com.justai.jaicf.template.util.intent

import com.justai.jaicf.api.BotRequest
import com.justai.jaicf.channel.yandexalice.api.AliceBotRequest

class IntentUtil() {

    fun isIntentPresent(request: BotRequest, intentType: IntentType): Boolean? {
        val aliceBotRequest: AliceBotRequest = request as AliceBotRequest
        return aliceBotRequest.request?.nlu?.intents?.contains(intentType.type)
    }

}
