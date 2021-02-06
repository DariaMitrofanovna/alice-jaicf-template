package com.justai.jaicf.template.trainer.states

import com.justai.jaicf.channel.yandexalice.api.AliceBotRequest
import com.justai.jaicf.channel.yandexalice.AliceReactions
import com.justai.jaicf.channel.yandexalice.alice
import com.justai.jaicf.context.ActionContext
import com.justai.jaicf.template.util.intent.IntentUtil
import com.justai.jaicf.template.util.user.UserUtil


abstract class State {

    val intentUtil = IntentUtil()
    val userUtil = UserUtil()

    fun handle(ctx: ActionContext<*, *, *>): State {
        val alice = ctx.reactions.alice!! // todo: doublecheck
        val request = ctx.request as AliceBotRequest

        return handleInternal(request, alice)
    }

    open fun handleInternal(request: AliceBotRequest, alice: AliceReactions): State {
        return this
    }
}

class InitialState1 : State() {

    override fun handleInternal(request: AliceBotRequest, alice: AliceReactions): State {
        alice.say(
            """
                Привет! Я тренер Боб (друг Алисы). Предлагаю устроить такую тренировку: бег, силовое упражнение, снова бег - и снова упражнение. 
                Повторять будем столько, сколько захотите. Ну что, готовы попотеть?
            """.trimIndent()
        )
        alice.buttons("да", "нет")

        //        alice.image(
//            "https://lh3.googleusercontent.com/proxy/qVzQV1VNXobPVxgYnUkwf_kghwvBNSKsHrcpAa1pwuufVamLwtTHvcOb7oyR79oDLZ9tLJ2DSfPV9MppzfhjoK1PnEo",
//            "",
//            "Привет! Я тренер Боб (друг Алисы). Предлагаю устроить такую тренировку: бег, силовое упражнение,  снова бег - и снова упражнение. Повторять будем столько, сколько захотите. Ну что, готовы попотеть?"
//        )

        //        val db: Db = FileDb()
//        db.write(ctx.request.clientId, "что-то")
//        var info = db.read(ctx.request.clientId + ".txt")

        return this
    }
}

//class Geolocation2 : State() {
//
//    override fun changeState(ctx: ActionContext): State {
//        val input = ctx.request.input
//        return if (input == "да") {
//            ctx.reactions.alice!!.buttons("ок", "не ок")
//            ctx.reactions.alice!!.say("Если дашь доступ к геолокации, я посчитаю итоговую дистанцию.")
//            Approve3()
//        } else Fallback().handle(ctx)
//    }
//}
//
//class Approve3 : State() {
//    override fun changeState(ctx: ActionContext): State {
//        val input = ctx.request.input
//        return if (input == "ок") {
//            ctx.reactions.alice!!.buttons("готов", "не готов")
//            ctx.reactions.alice!!.say("Понадобится место, где ты будешь бегать. Можешь выбрать сам что-то вроде стадиона или дорожки в парке или можешь бегать вокруг кремля. Скажи когда будешь готов, мы начнём тренировку")
//            Training4()
//        } else Fallback().handle(ctx)
//    }
//}
//
//class Training4 : State() {
//    override fun changeState(ctx: ActionContext): State {
//        val input = ctx.request.input
//        return if (input == "готов") {
//            ctx.reactions.alice!!.say("Беги")
//            End()
//        } else Fallback().handle(ctx)
//    }
//}
//
//
//class End : State() {
//    override fun changeState(ctx: ActionContext): State {
//        ctx.reactions.alice!!.say("Happy end")
//        return this
//    }
//}
//
//class Fallback : State() {
//    override fun changeState(ctx: ActionContext): State {
//        ctx.reactions.alice!!.buttons("ок")
//        ctx.reactions.alice!!.say("Что-то пошло не так, падаю и начинаю заново...")
//        return InitialState1()
//    }
//}


