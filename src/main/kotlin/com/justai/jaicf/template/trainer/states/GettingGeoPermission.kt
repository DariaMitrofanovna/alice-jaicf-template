package com.justai.jaicf.template.trainer.states

import com.justai.jaicf.api.BotRequest
import com.justai.jaicf.channel.yandexalice.AliceReactions
import kotlin.random.Random

class GettingGeoPermission : State() {


    override fun handleInternal(request: BotRequest, alice: AliceReactions): State {

        alice.say(
            """
                Понадобится место, где вы будете бегать.
                Можно выбрать готовую тренировку вокруг Кремля, или можете выбрать место сами (например, дорожку в парке или стадион).
                Что выберете?
            """.trimIndent()
        )

        if (false) { // todo: if geo enabled, return route, if not - return point
            alice.link(
                title = "Маршрут до Кремля",
                url = "https://yandex.ru/maps/24/veliky-novgorod/?ll=31.264094%2C58.523962&mode=routes&routes%5BactiveComparisonMode%5D=pedestrian&rtext=58.527206%2C31.257840~58.522093%2C31.272786&rtn=2&rtt=pd&ruri=~&z=16.09"
            )
        } else {
            alice.link(
                title = "Кремль на Картах",
                url = "https://yandex.ru/maps/-/CCUMMHTvdB"
            )
        }

        alice.buttons(
            "Вокруг Кремля", "Своё место"
        )

        return ChoosingPlace()
    }
}