package com.justai.jaicf.template.trainer

object UserSessionRepository {

    private val map = mutableMapOf<String, UserSession>()

    fun getOrCreate(userAppId: String): UserSession {
        return map[userAppId] ?: UserSession(userAppId).also { map[userAppId] = it }
    }
}