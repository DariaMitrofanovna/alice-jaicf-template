package com.justai.jaicf.template.trainer.excercises

object ExcerciseRepository {

    var agon = 0 // fixme

    fun getNextRandomExcercise(history: ExcerciseHistory): Exercise {
//        return Excercise.values()[agon++]

        val exceptBodyParts = mutableSetOf<BodyPart>()
        val historySize = history.exercises.size
        if (history.exercises.size > 0) {
            exceptBodyParts += history.exercises[historySize - 1].bodyPart
        }

        if (historySize > 1) {
            exceptBodyParts += history.exercises[historySize - 2].bodyPart
        }

        while (true) {
            val exc = Exercise.values().random()
            if (exc.bodyPart !in exceptBodyParts) {
                return exc
            }
        }
    }
}