package com.example.homeworks.small.simple02;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

@Disabled
class HomeworkLocatorsTest {

    @Test
    void goToEnterprisePage() {
        //Откройте страницу Selenide в Github
        open("https://github.com");
        //Зайти по ссылке Solutions > Enterprise (как пользователь, наведя и кликнув мышкой)
        $x("//header//button[contains(text(), 'Solutions')]").should(visible).hover();
        $x("//div[contains(@class, 'HeaderMenu-dropdown')]//a[contains(text(), 'Enterprise')]").should(visible).click();
        $x("//main//h1[contains(text(), 'Build like the best')]").should(visible);
    }

    @Test
    void dragAndDrop() {
        //Откройте https://the-internet.herokuapp.com/drag_and_drop
        open("https://the-internet.herokuapp.com/drag_and_drop");
        //Перенесите прямоугольник А на место В
        $("#column-a header").should(text("A"));
        $("#column-a").dragAndDropTo($("#column-b"));   //work
        //actions().moveToElement($("#column-a")).clickAndHold().moveByOffset(250, 0).release().perform();  //not work
        //actions().clickAndHold($("#column-a")).moveByOffset(250, 50).release().perform();  //not work
        //Проверьте, что прямоугольники действительно поменялись
        $("#column-a header").should(text("B"));
    }

}