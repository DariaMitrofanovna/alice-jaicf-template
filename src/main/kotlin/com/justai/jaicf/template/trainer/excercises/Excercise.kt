package com.justai.jaicf.template.trainer.excercises

import com.justai.jaicf.channel.yandexalice.api.model.Button
import com.justai.jaicf.channel.yandexalice.api.model.Image

private const val TITLE_PH = "(TITLE)"

enum class Excercise(
    val title: String,
    val titleTemplates: List<String>,
    val imageUrl: String,
    val bodyPart: BodyPart,
    val images: List<Image>,
) {
    SQUATS(
        title = "30 приседаний",
        titleTemplates = listOf("Делайте $TITLE_PH", "С вас $TITLE_PH", "$TITLE_PH, поехали!"),
        imageUrl = "https://smartliferd.tk/wp-content/uploads/2017/09/forma-perfecta-cuclillas.jpg",
        bodyPart = BodyPart.LEGS,
        images = listOf(
            Image("1521359/3329479b9db951a34f09"),
            Image("1533899/a895585a74f9c887040f"),
            Image("937455/cd422a05a97cdfec275c"),
        )
    ),
    PUSH_UPS(
        title = "20 обычных отжиманий",
        titleTemplates = listOf("Теперь делайте $TITLE_PH", "Делаем $TITLE_PH", "$TITLE_PH!"),
        imageUrl = "https://testkids.ru/wp-content/uploads/2019/10/отжимание.jpg",
        bodyPart = BodyPart.ARMS,
        images = listOf(
            Image("1521359/3329479b9db951a34f09"),
            Image("1533899/a895585a74f9c887040f"),
            Image("937455/cd422a05a97cdfec275c"),
        )
    ),
    PUSH_UPS_NARROW(
        title = "20 отжиманий узким хватом",
        titleTemplates = listOf("Сейчас $TITLE_PH", "Делаем $TITLE_PH", "$TITLE_PH!"),
        imageUrl = "https://www.azbukadiet.ru/wp-content/uploads/2017/09/nakachat-ruki-devushke-doma-11-360x240.jpg",
        bodyPart = BodyPart.ARMS,
        images = listOf(
            Image("1521359/3329479b9db951a34f09"),
            Image("1533899/a895585a74f9c887040f"),
            Image("937455/cd422a05a97cdfec275c"),
        )
    ),
    PUSH_UPS_WIDE(
        title = "20 отжиманий широким хватом",
        titleTemplates = listOf("Теперь делайте $TITLE_PH", "Делаем $TITLE_PH", "$TITLE_PH!"),
        imageUrl = "https://menrus.ru/wp-content/uploads/2018/08/e4cc98d13942e67e4f21b8270e51af7a.jpg",
        bodyPart = BodyPart.ARMS,
        images = listOf(
            Image("1521359/3329479b9db951a34f09"),
            Image("1533899/a895585a74f9c887040f"),
            Image("937455/cd422a05a97cdfec275c"),
        )
    ),
    DROPS_FRONT(
        title = "По 15 выпадов вперёд на каждую ногу",
        titleTemplates = listOf("Давайте теперь $TITLE_PH", "Делаем $TITLE_PH"),
        imageUrl = "https://kolizey36.ru/wp-content/uploads/bokovye-vypady-kakie-myshcy-rabotayut-i-kak-delat-uprazhnenie-pravilno.jpg",
        bodyPart = BodyPart.LEGS,
        images = listOf(
            Image("1521359/3329479b9db951a34f09"),
            Image("1533899/a895585a74f9c887040f"),
            Image("937455/cd422a05a97cdfec275c"),
        )
    ),
    DROPS_BACK(
        title = "По 15 выпадов назад на каждую ногу",
        titleTemplates = listOf("Внимание, $TITLE_PH", "Делаем $TITLE_PH", "$TITLE_PH!"),
        imageUrl = "https://kolizey36.ru/wp-content/uploads/bokovye-vypady-kakie-myshcy-rabotayut-i-kak-delat-uprazhnenie-pravilno.jpg",
        bodyPart = BodyPart.LEGS,
        images = listOf(
            Image("1521359/3329479b9db951a34f09"),
            Image("1533899/a895585a74f9c887040f"),
            Image("937455/cd422a05a97cdfec275c"),
        )
    ),
    BURPY(
        title = "10 бёрпи",
        titleTemplates = listOf("Теперь $TITLE_PH", "Готов? Дальше $TITLE_PH", "Молодец, теперь $TITLE_PH", "$TITLE_PH!"),
        imageUrl = "https://foreverhealth.ru/wp-content/uploads/2019/12/ef159bd80101f486a1983b6f37e72cdf.jpg",
        bodyPart = BodyPart.BODY,
        images = listOf(
            Image("965417/b4537c2649d815995fd1", "делай раз", "описание раз", Button("гифка", url = "https://rulebody.ru/wp-content/uploads/2018/05/upraznenie-berpi-5ad06afced553.gif")),
            Image("1540737/b8e9bdc4664f183e0393", "делай два"),
            Image("965417/283ecf7c7b0782589cb8", "делай три"),
            Image("1521359/1ccce06b297dd70182dc", "делай четыре"),
            Image("965417/b4537c2649d815995fd1", "делай пять"),
        )
    ),
    PLANK(
        title = "в планке 30 секунд",
        titleTemplates = listOf("Стоим $TITLE_PH", "Дальше стоим $TITLE_PH", "$TITLE_PH!"),
        imageUrl = "http://zdravieserd.ru/wp-content/public_images2/fc4e08990a7e7aa21aa45fec83bd1109-300x207.jpg",
        bodyPart = BodyPart.BODY,
        images = listOf(
            Image("1521359/3329479b9db951a34f09"),
            Image("1533899/a895585a74f9c887040f"),
            Image("937455/cd422a05a97cdfec275c"),
        )
    );

    private val titles = titleTemplates.map { it.replace(TITLE_PH, title) }

    fun genRandomTitle(): String {
        return titles.random()
    }
}