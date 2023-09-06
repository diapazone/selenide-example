package asserttest;

import com.codeborne.selenide.WebDriverRunner;
import core.BaseTest;
import org.assertj.core.api.SoftAssertions;

import static org.assertj.core.api.Assertions.*;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Тест-кейс:
 * 1. Открыть сайт Петрович по ссылке https://petrovich.ru/
 * 2. Перейти в категорию "Стройматериалы"
 * 2.1 Убедиться, что каталог содержит 17 подкатегорий
 * 3. Перейти в подкатегорию "Сваи"
 * 4. Отсортировать ассортимент по цене по возрастанию
 * 5. Проверить, что первые 4 товара имеют цену менее 300 рублей
 * 6. Проверить, что кнопка "В корзину" у первого товара кликабельна
 */

public class AssertTest extends BaseTest {
    private static final Logger log = LoggerFactory.getLogger(AssertTest.class);

    private static final String BASE_URL = "https://petrovich.ru/";

    @Test
    public void petrovichTest(){
        SoftAssertions softly = new SoftAssertions();

        PetrovichPage mainPage = new PetrovichPage(BASE_URL);
        mainPage.openCategory();
        softly.assertThat(WebDriverRunner.url())
                .as("URL не совпадает с каталогом Стройматериалов")
                .isEqualTo("https://petrovich.ru/catalog/245635348/");
        softly.assertThat(mainPage.countSubcategories())
                .as("количество подкатегорий должно быть 17")
                .isEqualTo(17);
        mainPage.openSubcategory();
        softly.assertThat(WebDriverRunner.url())
                .as("URL не совпадает с каталогом Свай")
                .isEqualTo("https://petrovich.ru/catalog/285396370/");
        mainPage.sortSubcategory();
        List<Integer> pricelist = mainPage.checkPrices().subList(0, 3);
        softly.assertThat(pricelist)
                .as("Первые 4 позиции должны иметь стоимость менее 300")
                .allSatisfy(price -> {
                    softly.assertThat(price).isLessThan(300);
                });
        mainPage.clickButton();
        assertThat(mainPage.button.text())
                .as("Товар должен быть в корзине")
                .isEqualTo("В корзине");

        softly.assertAll();
    }


}
