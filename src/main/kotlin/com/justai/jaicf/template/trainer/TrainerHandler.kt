package com.justai.jaicf.template.trainer

import com.justai.jaicf.channel.yandexalice.api.AliceBotRequest
import com.justai.jaicf.context.ActionContext
import com.justai.jaicf.template.db.FileDb
import java.time.LocalDate
import java.time.LocalTime

class TrainerHandler {

    fun handle(ctx: ActionContext<*, *, *>) {
        (ctx.request as? AliceBotRequest)?.also {
            val userAppId = it.session.application.applicationId
            val userSession = UserSessionRepository.getOrCreate(userAppId)

            FileDb.write(
                userAppId,
                buildString {
                    append("date: ${LocalDate.now()}; ")
                    append("time: ${LocalTime.now()}; ")
                    append("type: ${ctx.request.type}; ")
                    append("input: ${ctx.request.input}; ")
                    append("intents: ${(ctx.request as? AliceBotRequest)?.request?.nlu?.intents?.entries?.joinToString { it.key + " - " + it.value.toString() }}; ")
                }
            )

            userSession.handle(ctx)
        }
            ?: throw IllegalStateException("wtf")
    }
}