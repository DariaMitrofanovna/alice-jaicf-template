package com.justai.jaicf.template.trainer

class RandomPhrase(
    vararg variants: String
) {
    val random: String = variants.random()
}