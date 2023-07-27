package com.example.homeworks.small.simple01;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

@Disabled
class HomeworkLocatorsTest {

    @Test
    void sortAssertionsPageTest() {
        //Откройте страницу Selenide в Github
        open("https://github.com/selenide/selenide");

        //Перейдите в раздел Wiki проекта
        $("#wiki-tab").should(visible).click();

        //Убедитесь, что в списке страниц (Pages) есть страница SoftAssertions. Откройте страницу SoftAssertions
        $("#wiki-pages-filter").should(visible).setValue("SoftAssertions");
        $x("//div[contains(@class, 'wiki-rightbar')]//a[text()='SoftAssertions']").should(visible).click();

        //Проверьте что внутри есть пример кода для JUnit5
        $x("//h4[contains(text(),'JUnit5')]/following-sibling::div[1][contains(@class, 'highlight')]").should(visible).scrollTo();
    }

}