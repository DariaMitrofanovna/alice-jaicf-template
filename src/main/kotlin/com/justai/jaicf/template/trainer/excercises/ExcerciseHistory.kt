package com.justai.jaicf.template.trainer.excercises

class ExcerciseHistory private constructor(val excercises: ArrayList<Excercise> = arrayListOf()) {

    operator fun plus(next: Excercise): ExcerciseHistory {
        return ExcerciseHistory(
            ArrayList(excercises + next)
        )
    }

    companion object {
        fun empty(): ExcerciseHistory = ExcerciseHistory()
    }
}