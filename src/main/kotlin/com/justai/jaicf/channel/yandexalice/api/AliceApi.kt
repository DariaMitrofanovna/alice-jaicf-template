package com.justai.jaicf.channel.yandexalice.api

import com.justai.jaicf.channel.yandexalice.JSON
import io.ktor.client.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*

class AliceApi(
    oauthToken: String,
    private val skillId: String
) {

    companion object {
        private const val URL = "https://dialogs.yandex.net/api/v1"
        private val imageStorage = mutableMapOf<String, MutableMap<String, String>>()
    }

    private val images = mutableMapOf<String, String>()

    private val client = HttpClient() {
        expectSuccess = true

        install(JsonFeature) {
            serializer = KotlinxSerializer(JSON)
        }

        defaultRequest {
            header("Authorization", "OAuth $oauthToken")
        }
    }

    init {
//        images.putAll(
//            imageStorage.getOrPut(skillId) {
//                listImages().map { it.origUrl to it.id }.toMap().toMutableMap()
//            }
//        )
    }

//    fun getImageId(url: String) = images.getOrPut(url) { uploadImage(url).id }

//    fun uploadImage(url: String): Image = runBlocking {
//        client.post<UploadedImage>("$URL/skills/$skillId/images") {
//            contentType(ContentType.Application.Json)
//            body = JsonObject(mapOf("url" to JsonPrimitive(url)))
//        }.image
//    }.also { image ->
//        imageStorage[skillId]?.put(image.origUrl, image.id)
//    }

//    fun listImages(): List<Image> = runBlocking {
//        client.get<Images>("$URL/skills/$skillId/images").images
//    }
}