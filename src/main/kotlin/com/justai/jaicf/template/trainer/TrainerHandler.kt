package com.justai.jaicf.template.trainer

import com.justai.jaicf.api.BotRequest
import com.justai.jaicf.api.BotRequestType
import com.justai.jaicf.channel.yandexalice.AliceReactions
import com.justai.jaicf.channel.yandexalice.alice
import com.justai.jaicf.context.ActionContext

class TrainerHandler {

    var currentState: State = InitialState()

    fun handle(ctx: ActionContext) {
        // destruct the context
        request = ctx.request
        alice = ctx.reactions.alice!!

        currentState = currentState.handle(ctx)

        when (ctx.request.type) {
            BotRequestType.EVENT -> handleEvent()
            BotRequestType.QUERY -> handleQuery()
            else -> fallback()
        }
    }

    lateinit var request: BotRequest
    lateinit var alice: AliceReactions

    private fun fallback() {
        alice.say("Что-то пошло не так, падаю...")
    }

    private fun handleEvent() {
        if (request.input == "start") {
            alice.say(text = "Привет! Этот навык еще в разработке.")
        } else {
            fallback()
        }
    }

    private fun handleQuery() {
        alice.say(text = "Ты сказал: ${request.input}!!")
    }
}