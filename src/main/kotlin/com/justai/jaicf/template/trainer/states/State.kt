package com.justai.jaicf.template.trainer.states

import com.justai.jaicf.channel.yandexalice.AliceReactions
import com.justai.jaicf.channel.yandexalice.alice
import com.justai.jaicf.channel.yandexalice.api.AliceBotRequest
import com.justai.jaicf.context.ActionContext
import com.justai.jaicf.template.trainer.common_models.RandomPhrasesRepository

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
    open val fallbackButtons: List<List<String>> = listOf()

    protected fun fallback(request: AliceBotRequest, alice: AliceReactions): State {
        return when (fallbackDepth) {
            0, 1 -> {
                alice.say(fallbackTexts[fallbackDepth])
                alice.buttons(*fallbackButtons[fallbackDepth].toTypedArray())

                ++fallbackDepth
                this
            }
            2 -> {
                alice.say(RandomPhrasesRepository.terminateFallback.random)
                alice.buttons("Сначала")
                End()
            }

            // means 3
            else -> {
                throw IllegalStateException()
            }
        }
    }
}


