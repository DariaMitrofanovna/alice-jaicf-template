package com.justai.jaicf.template.trainer.states

import com.justai.jaicf.api.BotRequestType
import com.justai.jaicf.channel.yandexalice.AliceReactions
import com.justai.jaicf.channel.yandexalice.api.AliceBotRequest
import com.justai.jaicf.template.res.Links
import com.justai.jaicf.template.trainer.common_models.RandomPhrasesRepository

class GettingGeoPermission : State() {

    override val fallbackTexts: List<String> = listOf(
            "${RandomPhrasesRepository.notUnderstand.random} Разрешаете ли Вы геолокацию?",
            "Мне нужно знать, разрешаете ли Вы геолокацию. Нажмите, например, \"разрешаю\"")

    override val fallbackButtons: List<List<String>> = listOf(
            emptyList(), emptyList(), emptyList()
    )

    override fun handleInternal(request: AliceBotRequest, alice: AliceReactions): State {

        return when (request.type) {
            BotRequestType.GEO_ALLOWED -> {
                goodFlow(request, alice, true)
            }
            BotRequestType.GEO_REJECTED -> {
                goodFlow(request, alice, false)
            }
            else -> {
                if (request.input.equals("разрешить на 1 час")) {
                    goodFlow(request, alice, true)
                } else {
                    fallback(request, alice)
                }
            }
        }

    }

    fun goodFlow(request: AliceBotRequest, alice: AliceReactions, geo: Boolean): State {
        alice.say(
                """
                Понадобится место, где вы будете бегать.
                Можно выбрать готовую тренировку вокруг Кремля, или можете выбрать место сами (например, дорожку в парке или стадион).
                Что выберете?
            """.trimIndent()
        )

        val location = request.session.location
        if (geo && location != null) {
            alice.link(
                    title = "Маршрут до места старта",
                    url = Links.kremlinPointMapsUrl
                    // fixme: add route link
//                url = "https://yandex.ru"
//                ${location.lat}%2C${location.lon}
//                url = "https://maps.yandex.ru/?rtext=53.9170029,27.584480199999998~55.8675,37.5928"
//                url = "https://yandex.ru/maps/24/veliky-novgorod/?ll=${location.lat}%2C${location.lon}&mode=routes&rtext=58.522362%2C31.255854~58.522361%2C31.272050&rtt=pd&ruri=~&z=15.72"
//                url = "https://yandex.ru/maps/24/veliky-novgorod/?ll=31.263119%2C58.523408&mode=routes&rtext=58.522362%2C31.255854~58.522361%2C31.272050&rtt=pd&ruri=~&z=15.72"
            )
        } else {
            alice.link(
                    title = "Место старта на Картах",
                    url = Links.kremlinPointMapsUrl
            )
        }

        alice.buttons(
                "Вокруг Кремля", "Своё место"
        )

        return ChoosingPlace()
    }
}