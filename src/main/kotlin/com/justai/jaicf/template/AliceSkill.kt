package com.justai.jaicf.template

import com.justai.jaicf.BotEngine
import com.justai.jaicf.activator.catchall.CatchAllActivator
import com.justai.jaicf.activator.event.BaseEventActivator
import com.justai.jaicf.activator.regex.RegexActivator
import com.justai.jaicf.channel.yandexalice.activator.AliceIntentActivator
import com.justai.jaicf.template.trainer.TrainerScenario

val skill = BotEngine(
    model = TrainerScenario().model,
    activators = arrayOf(
        RegexActivator,
        BaseEventActivator,
        CatchAllActivator,
        AliceIntentActivator
    )
)