package com.justai.jaicf.channel.http

import java.io.ByteArrayOutputStream
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets

/**
 * Contains details of the HTTP responce returned by [HttpBotChannel]
 *
 * @property output stream that contains a response data
 * @property contentType the type of response ("application/json" for example)
 */
data class HttpBotResponse(
    val output: ByteArrayOutputStream,
    val contentType: String
) {
    val headers = mutableMapOf<String, String>()

    constructor(
        text: String,
        contentType: String,
        charset: Charset = StandardCharsets.UTF_8
    ): this(
        output = ByteArrayOutputStream(text.length).apply { write(text.toByteArray(charset)) },
        contentType = contentType
    )
}

fun String.asJsonHttpBotResponse() = HttpBotResponse(this, "application/json")
fun String.asTextHttpBotResponse() = HttpBotResponse(this, "text/plain")