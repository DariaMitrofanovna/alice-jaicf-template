package com.justai.jaicf.template.trainer

import com.justai.jaicf.api.BotRequestType
import com.justai.jaicf.context.ActionContext
import com.justai.jaicf.template.trainer.states.InitialState
import com.justai.jaicf.template.trainer.states.State

class UserSession(
    private val userAppId: String
) {
    private val initialState: State
        get() = InitialState()

    private var state: State = initialState

    fun handle(ctx: ActionContext) {
        // dialog cleared event
        if (ctx.request.type == BotRequestType.EVENT && ctx.request.input == "start") {
            state = initialState
        }

        state = state.handle(ctx)
    }
}