package com.justai.jaicf.template.trainer

import com.justai.jaicf.api.BotRequest
import com.justai.jaicf.channel.yandexalice.AliceReactions
import com.justai.jaicf.channel.yandexalice.alice
import com.justai.jaicf.context.ActionContext

class TrainerHandler {

    var currentState: State = InitialState1()

    fun handle(ctx: ActionContext) {
        // destruct the context
        request = ctx.request
        alice = ctx.reactions.alice!!
        currentState = currentState.handle(ctx)

    }

    lateinit var request: BotRequest
    lateinit var alice: AliceReactions
}