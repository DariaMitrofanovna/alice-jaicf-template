package com.justai.jaicf.template.trainer

import com.justai.jaicf.channel.yandexalice.api.AliceBotRequest
import com.justai.jaicf.context.ActionContext
import com.justai.jaicf.template.db.FileDb

class TrainerHandler {

    fun handle(ctx: ActionContext) {
        (ctx.request as? AliceBotRequest)?.also {
            val userAppId = it.session.application.applicationId
            val userSession = UserSessionRepository.getOrCreate(userAppId)

            FileDb.write(
                userAppId,
                buildString {
                    append("type: ${ctx.request.type};")
                    append("input: ${ctx.request.input};")
                    appendln()
                }
            )

            userSession.handle(ctx)
        }
            ?: throw IllegalStateException("wtf")
    }
}