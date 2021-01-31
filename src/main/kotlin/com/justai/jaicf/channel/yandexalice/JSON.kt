package com.justai.jaicf.channel.yandexalice

import com.justai.jaicf.channel.yandexalice.api.model.Card
import com.justai.jaicf.channel.yandexalice.api.model.Image
import com.justai.jaicf.channel.yandexalice.api.model.ItemsList
import kotlinx.serialization.json.Json
import kotlinx.serialization.modules.SerializersModule

internal val JSON = Json {
    this.classDiscriminator = "type"
    this.ignoreUnknownKeys = true
    this.encodeDefaults = true
//    this.serializersModule = SerializersModule({polymorphic(Card::class, Image::class, Image.serializer())})  DSL
    this.serializersModule = SerializersModule {
        polymorphic(Card::class, Image::class, Image.serializer())
        polymorphic(Card::class, ItemsList::class, ItemsList.serializer())
    }

}
