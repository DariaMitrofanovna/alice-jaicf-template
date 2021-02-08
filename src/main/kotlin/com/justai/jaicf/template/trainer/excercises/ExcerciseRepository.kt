package com.justai.jaicf.template.trainer.excercises

object ExcerciseRepository {

    var agon = 5

    fun getNextRandomExcercise(history: ExcerciseHistory, kremlin: Boolean, level: Int): Excercise {
//        return Exercise.values()[agon++] // fixme

        val exceptBodyParts = mutableSetOf<BodyPart>()
        val historySize = history.excercises.size
        if (history.excercises.size > 0) {
            exceptBodyParts += history.excercises[historySize - 1].bodyPart
        }

        if (historySize > 1) {
            exceptBodyParts += history.excercises[historySize - 2].bodyPart
        }

        // hardcode pull ups kremlin
        if (kremlin && level == 1) {
            return Excercise.PULL_UPS
        }

        if (kremlin && level == 6) {
            return Excercise.GOOSE
        }

        // random exc
        while (true) {
            val exc = Excercise.values().random()
            if (exc.bodyPart !in exceptBodyParts) {
                if (exc != Excercise.PULL_UPS) {
                    return exc
                }
            }
        }
    }
}