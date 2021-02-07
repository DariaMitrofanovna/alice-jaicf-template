package com.justai.jaicf.template.trainer.excercises

import com.justai.jaicf.channel.yandexalice.api.model.Image

private const val TITLE_PH = "(TITLE)"
private const val QUANTITY_PH = "(QUANTITY)"

enum class Excercise(
    private val titleTemplate: String,
    private val titleTemplates: List<String>,
    val imageId: String = "1656841/bec994b6fc878b1a2746",
    val bodyPart: BodyPart,
    val countable: Boolean = true, // false means timeable
    val quantity: Pair<Int, Int> // light to hard
) {
    // legs
    SQUATS(
        titleTemplate = "$QUANTITY_PH приседаний",
        titleTemplates = listOf("Делайте $TITLE_PH", "С вас $TITLE_PH", "$TITLE_PH, поехали!"),
        bodyPart = BodyPart.LEGS,
        countable = true,
        quantity = 10 to 20,
    ),
    DROPS_FRONT(
        titleTemplate = "По $QUANTITY_PH выпадов вперёд на каждую ногу",
        titleTemplates = listOf("Давайте теперь $TITLE_PH", "Делаем $TITLE_PH"),
        bodyPart = BodyPart.LEGS,
        countable = true,
        quantity = 10 to 15,
    ),
    CHAIR(
        titleTemplate = "Стульчик $QUANTITY_PH секунд",
        titleTemplates = listOf("Внимание, $TITLE_PH", "Делаем $TITLE_PH", "$TITLE_PH!"),
        bodyPart = BodyPart.LEGS,
        countable = false,
        quantity = 30 to 60,
    ),
    GOOSE(
        titleTemplate = "Ходьба гуськом $QUANTITY_PH шагов",
        titleTemplates = listOf("Внимание, $TITLE_PH", "Делаем $TITLE_PH", "$TITLE_PH!"),
        bodyPart = BodyPart.LEGS,
        countable = true,
        quantity = 20 to 40,
    ),

//    DROPS_BACK(
//        title = "По 15 выпадов назад на каждую ногу",
//        titleTemplates = listOf("Внимание, $TITLE_PH", "Делаем $TITLE_PH", "$TITLE_PH!"),
//        bodyPart = BodyPart.LEGS,
//    ),

    // arms
    PUSH_UPS(
        titleTemplate = "$QUANTITY_PH обычных отжиманий",
        titleTemplates = listOf("Теперь делайте $TITLE_PH", "Делаем $TITLE_PH", "$TITLE_PH!"),
        bodyPart = BodyPart.ARMS,
        countable = true,
        quantity = 10 to 20,
    ),
    PUSH_UPS_NARROW(
        titleTemplate = "$QUANTITY_PH отжиманий узким хватом",
        titleTemplates = listOf("Сейчас $TITLE_PH", "Делаем $TITLE_PH", "$TITLE_PH!"),
        bodyPart = BodyPart.ARMS,
        countable = true,
        quantity = 5 to 10,
    ),
    PUSH_UPS_WIDE(
        titleTemplate = "$QUANTITY_PH отжиманий широким хватом",
        titleTemplates = listOf("Теперь делайте $TITLE_PH", "Делаем $TITLE_PH", "$TITLE_PH!"),
        bodyPart = BodyPart.ARMS,
        countable = true,
        quantity = 5 to 10,
    ),

    // body
    BURPY(
        titleTemplate = "$QUANTITY_PH бёрпи",
        titleTemplates = listOf(
            "Теперь $TITLE_PH",
            "Готов? Дальше $TITLE_PH",
            "Молодец, теперь $TITLE_PH",
            "$TITLE_PH!"
        ),
        bodyPart = BodyPart.BODY,
        countable = true,
        quantity = 5 to 10,
    ),
    PLANK(
        titleTemplate = "Планка $QUANTITY_PH секунд",
        titleTemplates = listOf("Стоим $TITLE_PH", "Дальше стоим $TITLE_PH", "$TITLE_PH!"),
        bodyPart = BodyPart.BODY,
        countable = false,
        quantity = 30 to 60,
    );

    fun title(hard: Boolean): String {
        return titleTemplate.replace(
            QUANTITY_PH,
            (if (hard) quantity.second else quantity.first).toString()
        )
    }

    fun genRandomTitle(hard: Boolean): String {
        return title(hard) // todo: random from templates
    }
}