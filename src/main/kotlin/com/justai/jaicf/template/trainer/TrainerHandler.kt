package com.justai.jaicf.template.trainer

import com.justai.jaicf.api.BotRequest
import com.justai.jaicf.channel.yandexalice.AliceReactions
import com.justai.jaicf.channel.yandexalice.alice
import com.justai.jaicf.context.ActionContext

class TrainerHandler {

    lateinit var request: BotRequest
    lateinit var alice: AliceReactions

    fun handle(ctx: ActionContext) {
        // destruct the context
        request = ctx.request
        alice = ctx.reactions.alice!!

        // do some work
        handleInternal()
    }

    private fun handleInternal() {
        when (val input = request.input) {
            "start" -> {
                alice.say(text = "Привет! Этот навык еще в разработке.")
            }

            else -> {
                alice.say(text = "Ты сказал: ${input}")
            }
        }
    }
}