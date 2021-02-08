package com.justai.jaicf.template.trainer.states

import com.justai.jaicf.channel.yandexalice.AliceReactions
import com.justai.jaicf.channel.yandexalice.alice
import com.justai.jaicf.channel.yandexalice.api.AliceBotRequest
import com.justai.jaicf.context.ActionContext
import com.justai.jaicf.template.trainer.common_models.RandomPhrasesRepository
import com.justai.jaicf.template.util.intent.SimpleIntent
import com.justai.jaicf.template.util.intent.hasSimpleIntent

abstract class State {

    fun handle(ctx: ActionContext<*, *, *>): State {
        val alice = ctx.reactions.alice!!
        val request = ctx.request as AliceBotRequest

        return handleInternal(request, alice)
    }

    open fun handleInternal(request: AliceBotRequest, alice: AliceReactions): State {
        return this
    }

//    open fun handleInternal(request: AliceBotRequest, alice: AliceReactions): State {
//        when {
//            request.hasSimpleIntent(SimpleIntent.YANDEX_HELP) -> {
//                alice.say("ПОМОЩЬ")
//            }
//
//            request
//        }
//    }

    protected var fallbackDepth = 0

    open val fallbackTexts: List<String> = listOf()
    open val fallbackButtons: List<List<String>> = listOf()

    protected fun fallback(request: AliceBotRequest, alice: AliceReactions): State {

        if (request.hasSimpleIntent(SimpleIntent.YANDEX_HELP)) {
            alice.say(
                """
                   Я - Олег, Ваш персональный тренер.
                   Я могу устроить для Вас тренировку с бегом и упражнениями для приведения тела в тонус и поднятия настроения.
                   Я буду говорить и показывать, что конкретно и как долго делать - проработаем все тело. Можно выбрать время тренировки, а также легкий или сложный уровни.
                   
                   ${fallbackTexts[1]}
                """.trimIndent()
            )
            alice.buttons(*fallbackButtons[1].toTypedArray())
            return this
        }

        if (request.hasSimpleIntent(SimpleIntent.YANDEX_WHAT_CAN_YOU_DO)) {
            alice.say(
                """
                   Отличный вопрос, я ждал его!
                   Я могу устроить для Вас тренировку с бегом и упражнениями для приведения тела в тонус и поднятия настроения.
                   Я буду говорить и показывать, что конкретно и как долго делать - проработаем все тело. Можно выбрать время тренировки, а также легкий или сложный уровни.
                   
                   ${fallbackTexts[1]}
                """.trimIndent()
            )
            alice.buttons(*fallbackButtons[1].toTypedArray())
            return this
        }

        return when (fallbackDepth) {
            0, 1 -> {
                alice.say(fallbackTexts[fallbackDepth])
                alice.buttons(*fallbackButtons[fallbackDepth].toTypedArray())
                if (this is GettingGeoPermission)
                    alice.geoPermissionRequest()
                ++fallbackDepth
                this
            }
            2 -> {
                alice.say(RandomPhrasesRepository.terminateFallback.random)
                alice.buttons("Сначала")
                RepeatToBeginning()
            }

            // means 3
            else -> {
                throw IllegalStateException()
            }
        }
    }
}


