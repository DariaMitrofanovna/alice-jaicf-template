package com.justai.jaicf.template.db

import java.io.File

abstract class Db {
    abstract fun read(userId: String): String
    abstract fun write(userId: String, info: String)
}

class FileDb : Db() {
    override fun read(userId: String): String {
        val content: String
        content = if (File("$userId.txt").exists()) {
            File(userId).readText(Charsets.UTF_8)
        } else {
            "no info"
        }
        return content
    }

    override fun write(userId: String, info: String) {
        if (File("$userId.txt").exists()) {
            File(userId).writeText(info, Charsets.UTF_8)
        } else {
            val file = File("$userId.txt");
            file.createNewFile();
            file.writeText(info, Charsets.UTF_8)
        }
    }


}