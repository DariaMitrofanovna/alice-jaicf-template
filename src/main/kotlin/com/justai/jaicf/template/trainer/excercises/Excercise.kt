package com.justai.jaicf.template.trainer.excercises

import com.justai.jaicf.channel.yandexalice.api.model.Button
import com.justai.jaicf.channel.yandexalice.api.model.Image

private const val TITLE_PH = "(TITLE)"

enum class Excercise(
    val title: String,
    val titleTemplates: List<String>,
    val image: Image = Image(
        "1656841/bec994b6fc878b1a2746",
        title = title
    ),
    val bodyPart: BodyPart
) {
    // legs
    SQUATS(
        title = "30 приседаний",
        titleTemplates = listOf("Делайте $TITLE_PH", "С вас $TITLE_PH", "$TITLE_PH, поехали!"),
        bodyPart = BodyPart.LEGS,
    ),
    DROPS_FRONT(
        title = "По 15 выпадов вперёд на каждую ногу",
        titleTemplates = listOf("Давайте теперь $TITLE_PH", "Делаем $TITLE_PH"),
        bodyPart = BodyPart.LEGS,
    ),
    CHAIR(
        title = "Стульчик 30 минут",
        titleTemplates = listOf("Внимание, $TITLE_PH", "Делаем $TITLE_PH", "$TITLE_PH!"),
        bodyPart = BodyPart.LEGS,
    ),
    GOOSE(
        title = "Стульчик 30 минут",
        titleTemplates = listOf("Внимание, $TITLE_PH", "Делаем $TITLE_PH", "$TITLE_PH!"),
        bodyPart = BodyPart.LEGS,
    ),

//    DROPS_BACK(
//        title = "По 15 выпадов назад на каждую ногу",
//        titleTemplates = listOf("Внимание, $TITLE_PH", "Делаем $TITLE_PH", "$TITLE_PH!"),
//        bodyPart = BodyPart.LEGS,
//    ),

    // arms
    PUSH_UPS(
        title = "20 обычных отжиманий",
        titleTemplates = listOf("Теперь делайте $TITLE_PH", "Делаем $TITLE_PH", "$TITLE_PH!"),
        bodyPart = BodyPart.ARMS,
    ),
    PUSH_UPS_NARROW(
        title = "20 отжиманий узким хватом",
        titleTemplates = listOf("Сейчас $TITLE_PH", "Делаем $TITLE_PH", "$TITLE_PH!"),
        bodyPart = BodyPart.ARMS,
    ),
    PUSH_UPS_WIDE(
        title = "20 отжиманий широким хватом",
        titleTemplates = listOf("Теперь делайте $TITLE_PH", "Делаем $TITLE_PH", "$TITLE_PH!"),
        bodyPart = BodyPart.ARMS,
    ),

    // body
    BURPY(
        title = "10 бёрпи",
        titleTemplates = listOf(
            "Теперь $TITLE_PH",
            "Готов? Дальше $TITLE_PH",
            "Молодец, теперь $TITLE_PH",
            "$TITLE_PH!"
        ),
        bodyPart = BodyPart.BODY,
    ),
    PLANK(
        title = "в планке 30 секунд",
        titleTemplates = listOf("Стоим $TITLE_PH", "Дальше стоим $TITLE_PH", "$TITLE_PH!"),
        bodyPart = BodyPart.BODY,
    );

    private val titles = titleTemplates.map { it.replace(TITLE_PH, title) }

    fun genRandomTitle(): String {
        return titles.random()
    }
}