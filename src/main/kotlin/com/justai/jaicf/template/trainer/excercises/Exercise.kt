package com.justai.jaicf.template.trainer.excercises

import com.justai.jaicf.template.res.Audios

private const val QUANTITY_PH = "(QUANTITY)"

enum class Exercise(
    private val titleTemplate: String,
    val imageId: String = "1656841/bec994b6fc878b1a2746",
    val bodyPart: BodyPart,
    val countable: Boolean = true, // false means timeable
    val quantity: Pair<Int, Int>, // light to hard
    val slow: Boolean = false
) {
    // legs
    SQUATS(
        titleTemplate = "$QUANTITY_PH приседаний",
        bodyPart = BodyPart.LEGS,
        imageId = "213044/f1352ec34585f556213c",
        countable = true,
        quantity = 10 to 20,
    ),
    DROPS_FRONT(
        titleTemplate = "$QUANTITY_PH выпадов вперёд суммарно на обе ноги",
        bodyPart = BodyPart.LEGS,
        imageId = "1533899/52d2e37947bd8878fc25",
        countable = true,
        quantity = 20 to 30,
    ),
    CHAIR(
        titleTemplate = "Стульчик $QUANTITY_PH секунд",
        bodyPart = BodyPart.LEGS,
        imageId = "213044/5beb739aac60d4ab7317",
        countable = false,
        quantity = 30 to 60,
    ),
    GOOSE(
        titleTemplate = "Ходьба гуськом $QUANTITY_PH шагов",
        bodyPart = BodyPart.LEGS,
        imageId = "1030494/136d74304fa2aa379da7",
        countable = true,
        quantity = 20 to 30,
    ),

    // arms
    PUSH_UPS(
        titleTemplate = "$QUANTITY_PH обычных отжиманий",
        bodyPart = BodyPart.ARMS,
        imageId = "213044/4c477dd25fb978bdbefd",
        countable = true,
        quantity = 10 to 20,
    ),
    PUSH_UPS_NARROW(
        titleTemplate = "$QUANTITY_PH отжиманий узким хватом",
        bodyPart = BodyPart.ARMS,
        imageId = "213044/4c477dd25fb978bdbefd", // fixme
        countable = true,
        quantity = 5 to 10,
        slow = true
    ),
    PUSH_UPS_WIDE(
        titleTemplate = "$QUANTITY_PH отжиманий широким хватом",
        bodyPart = BodyPart.ARMS,
        imageId = "213044/4c477dd25fb978bdbefd", // fixme
        countable = true,
        quantity = 5 to 10,
        slow = true
    ),
    PULL_UPS( // todo: hardcode
        titleTemplate = "$QUANTITY_PH подтягиваний на турнике",
        bodyPart = BodyPart.ARMS,
        imageId = "213044/4c477dd25fb978bdbefd", // fixme
        countable = true,
        quantity = 5 to 10,
        slow = true
    ),

    // body
    BURPY(
        titleTemplate = "$QUANTITY_PH бёрпи",
        bodyPart = BodyPart.BODY,
        imageId = "213044/4c477dd25fb978bdbefd", // fixme
        countable = true,
        quantity = 5 to 10,
        slow = true
    ),
    PLANK(
        titleTemplate = "Планка $QUANTITY_PH секунд",
        bodyPart = BodyPart.BODY,
        imageId = "213044/4c477dd25fb978bdbefd", // fixme
        countable = false,
        quantity = 30 to 60,
    );

    // **************************

    fun title(hard: Boolean): String {
        return titleTemplate.replace(
            QUANTITY_PH,
            (if (hard) quantity.second else quantity.first).toString()
        )
    }

    fun genRandomTitle(hard: Boolean): String {
        return title(hard)
    }

    fun music(hard: Boolean): String {
        val count = if (hard) quantity.second else quantity.first
        println("agon: music count=$count, ${if (countable) "counts" else "static"}, ${if (slow) "slow" else "fast"}")
        return if (countable) {
            if (slow) {
                when (count) {
                    5 -> Audios.music_5_times_slow
                    10 -> Audios.music_10_times_slow
                    else -> throw IllegalStateException("wtf")
                }
            } else {
                when (count) {
                    5 -> Audios.music_5_times_fast
                    10 -> Audios.music_10_times_fast
                    15 -> Audios.music_15_times_fast
                    20 -> Audios.music_20_times_fast
                    30 -> Audios.music_30_times_fast
                    else -> throw IllegalStateException("wtf")
                }
            }
        } else {
            when (count) {
                30 -> Audios.static_30_sec
                60 -> Audios.static_60_sec
                else -> throw IllegalStateException("wtf")
            }
        }
    }
}