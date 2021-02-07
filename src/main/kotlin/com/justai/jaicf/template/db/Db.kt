package com.justai.jaicf.template.db

import java.io.File

abstract class Db {
    abstract fun read(userId: String): String
    abstract fun write(userId: String, info: String)
}

object FileDb : Db() {

    private fun file(userId: String): File {
        return File("logs/$userId.txt")
    }

    override fun read(userId: String): String {
        val content: String
        val file = file(userId)
        content = if (file.exists()) {
            file.readText(Charsets.UTF_8)
        } else {
            "no info"
        }
        return content
    }

    override fun write(userId: String, info: String) {
        val file = file(userId)
        if (file.exists()) {
            file.appendText(info, Charsets.UTF_8)
            file.appendText("\n", Charsets.UTF_8)
        } else {
            file.createNewFile();
            file.writeText(info, Charsets.UTF_8)
        }
    }
}