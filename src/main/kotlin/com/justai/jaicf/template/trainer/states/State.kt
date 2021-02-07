package com.justai.jaicf.template.trainer.states

import com.justai.jaicf.channel.yandexalice.AliceReactions
import com.justai.jaicf.channel.yandexalice.alice
import com.justai.jaicf.channel.yandexalice.api.AliceBotRequest
import com.justai.jaicf.context.ActionContext

abstract class State {

    fun handle(ctx: ActionContext<*, *, *>): State {
        val alice = ctx.reactions.alice!! // todo: doublecheck
        val request = ctx.request as AliceBotRequest

        return handleInternal(request, alice)
    }

    open fun handleInternal(request: AliceBotRequest, alice: AliceReactions): State {
        return this
    }

    protected var fallbackDepth = 0

    // fixme
//    abstract val fallbackTexts: List<String>
    open val fallbackTexts: List<String> = listOf()

    protected fun fallback(request: AliceBotRequest, alice: AliceReactions): State {
//        alice.say("fallback")
//        alice.endSession()
//        return this


        when (fallbackDepth) {
            0 -> {
                alice.say(fallbackTexts[0])
            }

            1 -> {
                alice.say(fallbackTexts[1])
            }

            2 -> {
                alice.say(fallbackTexts[2])
            }
        }
        fallbackDepth++
        return this
    }
}


