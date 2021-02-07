package com.justai.jaicf.template.trainer.excercises

class ExcerciseHistory private constructor(val exercises: ArrayList<Exercise> = arrayListOf()) {

    operator fun plus(next: Exercise): ExcerciseHistory {
        return ExcerciseHistory(
            ArrayList(exercises + next)
        )
    }

    companion object {
        fun empty(): ExcerciseHistory = ExcerciseHistory()
    }
}