package com.justai.jaicf.template.util.intent

import com.justai.jaicf.channel.yandexalice.api.AliceBotRequest
import kotlinx.serialization.json.jsonPrimitive
import java.time.Duration
import kotlin.time.ExperimentalTime

private const val INTENT = "duration"
private const val SLOT_MINCOUNT = "mincount"

data class DurationIntent @ExperimentalTime constructor(
    val duration: Duration
)

@ExperimentalTime
fun AliceBotRequest.durationIntent(): DurationIntent? {
    return intent(INTENT)?.slots?.let { slots ->
        slots[SLOT_MINCOUNT]?.value?.let { mincount ->
            mincount.jsonPrimitive.content.toLongOrNull()?.let {
                DurationIntent(
                    Duration.ofMinutes(it)
                )
            }
        }
    }
}



