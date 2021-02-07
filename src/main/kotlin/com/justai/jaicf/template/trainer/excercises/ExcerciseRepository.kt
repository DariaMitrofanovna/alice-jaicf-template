package com.justai.jaicf.template.trainer.excercises

object ExcerciseRepository {

    fun getNextRandomExcercise(history: ExcerciseHistory): Excercise {

        return Excercise.BURPY

        val exceptBodyParts = mutableSetOf<BodyPart>()
        val historySize = history.excercises.size
        if (history.excercises.size > 0) {
            exceptBodyParts += history.excercises[historySize - 1].bodyPart
        }

        if (historySize > 1) {
            exceptBodyParts += history.excercises[historySize - 2].bodyPart
        }

        while (true) {
            val exc = Excercise.values().random()
            if (exc.bodyPart !in exceptBodyParts) {
                return exc
            }
        }
    }
}