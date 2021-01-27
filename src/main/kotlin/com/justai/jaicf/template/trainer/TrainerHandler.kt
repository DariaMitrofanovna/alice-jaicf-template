package com.justai.jaicf.template.trainer

import com.justai.jaicf.channel.yandexalice.api.AliceBotRequest
import com.justai.jaicf.context.ActionContext

class TrainerHandler {

    fun handle(ctx: ActionContext) {
        (ctx.request as? AliceBotRequest)?.also {
            val userAppId = it.session.application.applicationId
            val userSession = UserSessionRepository.getOrCreate(userAppId)
            userSession.handle(ctx)
        }
            ?: throw IllegalStateException("wtf")
    }
}