package com.justai.jaicf.template.trainer.common_models

import com.justai.jaicf.template.trainer.RandomPhrase

object RandomPhrasesRepository {
    val notUnderstand = RandomPhrase(
        "Не до конца Вас понял.",
        "Не совсем понял Вас.",
        "Не расслышал.",
        "Что-то не могу понять Вас.",
        "Не могу понять, о чем Вы.",
    )

    val terminateFallback = RandomPhrase(
        "Все таки не могу понять Вас. Начнем с начала или в другой раз?",
        "Все же не понимаю. Повторить всё с самого начала?",
        "Похоже, я так не умею. Можем вернуться в самое начало.",
        "Видимо, мы говорим на разных языках. Попробуем снова, или не сегодня?",
    )
}