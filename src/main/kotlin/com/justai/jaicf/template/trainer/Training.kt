package com.justai.jaicf.template.trainer

object TrainingRepository {

    fun getRandomExcercise(exceptType: ExcerciseType?): Excercise {
        while (true) {
            val exc = Excercise.values().random()
            if (exc.type != exceptType) {
                return exc
            }
        }
    }
}

private val TITLE = "(TITLE)"

enum class ExcerciseType {
    LEGS, ARMS, BODY
}

enum class Excercise(
    val title: String,
    val titleTemplates: List<String>,
    val imageUrl: String,
    val type: ExcerciseType,
    val imageIds: List<String>
) {
    SQUATS(
        title = "30 приседаний",
        titleTemplates = listOf("Делайте $TITLE", "С вас $TITLE", "$TITLE, поехали!"),
        imageUrl = "https://smartliferd.tk/wp-content/uploads/2017/09/forma-perfecta-cuclillas.jpg",
        type = ExcerciseType.LEGS,
        imageIds = listOf("1521359/3329479b9db951a34f09", "1533899/a895585a74f9c887040f", "937455/cd422a05a97cdfec275c")
    ),
    PUSH_UPS(
        title = "20 обычных отжиманий",
        titleTemplates = listOf("Теперь делайте $TITLE", "Делаем $TITLE", "$TITLE!"),
        imageUrl = "https://testkids.ru/wp-content/uploads/2019/10/отжимание.jpg",
        type = ExcerciseType.ARMS,
        imageIds = listOf("1521359/3329479b9db951a34f09", "1533899/a895585a74f9c887040f")
    ),
    PUSH_UPS_NARROW(
        title = "20 отжиманий узким хватом",
        titleTemplates = listOf("Сейчас $TITLE", "Делаем $TITLE", "$TITLE!"),
        imageUrl = "https://www.azbukadiet.ru/wp-content/uploads/2017/09/nakachat-ruki-devushke-doma-11-360x240.jpg",
        type = ExcerciseType.ARMS,
        imageIds = listOf("1521359/3329479b9db951a34f09", "1533899/a895585a74f9c887040f")
    ),
    PUSH_UPS_WIDE(
        title = "20 отжиманий широким хватом",
        titleTemplates = listOf("Теперь делайте $TITLE", "Делаем $TITLE", "$TITLE!"),
        imageUrl = "https://menrus.ru/wp-content/uploads/2018/08/e4cc98d13942e67e4f21b8270e51af7a.jpg",
        type = ExcerciseType.ARMS,
        imageIds = listOf("1521359/3329479b9db951a34f09", "1533899/a895585a74f9c887040f")
    ),
    DROPS_FRONT(
        title = "По 15 выпадов вперёд на каждую ногу",
        titleTemplates = listOf("Давайте теперь $TITLE", "Делаем $TITLE"),
        imageUrl = "https://kolizey36.ru/wp-content/uploads/bokovye-vypady-kakie-myshcy-rabotayut-i-kak-delat-uprazhnenie-pravilno.jpg",
        type = ExcerciseType.LEGS,
        imageIds = listOf("1521359/3329479b9db951a34f09", "1533899/a895585a74f9c887040f")
    ),
    DROPS_BACK(
        title = "По 15 выпадов назад на каждую ногу",
        titleTemplates = listOf("Внимание, $TITLE", "Делаем $TITLE", "$TITLE!"),
        imageUrl = "https://kolizey36.ru/wp-content/uploads/bokovye-vypady-kakie-myshcy-rabotayut-i-kak-delat-uprazhnenie-pravilno.jpg",
        type = ExcerciseType.LEGS,
        imageIds = listOf("1521359/3329479b9db951a34f09", "1533899/a895585a74f9c887040f")
    ),
    BURPY(
        title = "10 бёрпи",
        titleTemplates = listOf("Теперь $TITLE", "Готов? Дальше $TITLE", "Молодец, теперь $TITLE", "$TITLE!"),
        imageUrl = "https://foreverhealth.ru/wp-content/uploads/2019/12/ef159bd80101f486a1983b6f37e72cdf.jpg",
        type = ExcerciseType.BODY,
        imageIds = listOf("1521359/3329479b9db951a34f09", "1533899/a895585a74f9c887040f")
    ),
    PLANK(
        title = "в планке 30 секунд",
        titleTemplates = listOf("Стоим $TITLE", "Дальше стоим $TITLE", "$TITLE!"),
        imageUrl = "http://zdravieserd.ru/wp-content/public_images2/fc4e08990a7e7aa21aa45fec83bd1109-300x207.jpg",
        type = ExcerciseType.BODY,
        imageIds = listOf("1521359/3329479b9db951a34f09", "1533899/a895585a74f9c887040f")
    );

    private val titles = titleTemplates.map { it.replace(TITLE, title) }

    fun genRandomTitle(): String {
        return titles.random()
    }
}