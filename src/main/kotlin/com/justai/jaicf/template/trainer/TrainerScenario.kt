package com.justai.jaicf.template.trainer

import com.justai.jaicf.channel.yandexalice.alice
import com.justai.jaicf.channel.yandexalice.model.AliceEvent
import com.justai.jaicf.model.scenario.Scenario

class TrainerScenario : Scenario() {

    private val handler = TrainerHandler()

    init {
        state("main") {
            activators {
                this.catchAll()
                this.event(AliceEvent.START)
                this.event(AliceEvent.ACCOUNT_LINKING_COMPLETE)
            }

            action {
                handler.handle(this)
            }
        }
    }
}