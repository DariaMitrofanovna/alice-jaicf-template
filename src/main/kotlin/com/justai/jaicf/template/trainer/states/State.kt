package com.justai.jaicf.template.trainer

import com.justai.jaicf.channel.yandexalice.activator.alice
import com.justai.jaicf.channel.yandexalice.alice
import com.justai.jaicf.context.ActionContext

abstract class State {
    abstract fun handle(ctx: ActionContext): State
}

class InitialState : State() {
    override fun handle(ctx: ActionContext): State {
        val input = ctx.request.input
        ctx.reactions.alice!!.say("agon")

        if (input == "start") {
            return Yes()
        } else {
            return No()
        }
    }
}

class Yes : State() {

    val db = SpringSingletones.db

    override fun handle(ctx: ActionContext): State {
        return this
    }
}

class No(
    val base: String
) : State() {

    constructor() : this("")

    override fun handle(ctx: ActionContext): State {
        return this
    }
}

