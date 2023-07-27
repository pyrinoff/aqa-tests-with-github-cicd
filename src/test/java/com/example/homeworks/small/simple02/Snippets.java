package com.example.homeworks.small.simple02;

import com.codeborne.selenide.*;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.Keys;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.Duration;

import static com.codeborne.selenide.CollectionCondition.*;
import static com.codeborne.selenide.Condition.empty;
import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

// this is not a full list, just the most common
public class Snippets {

    void browser_command_examples() {

        //Открытие страницы. Полный URL
        open("https://google.com");
        //Открытие страницы. URL без хоста
        //Хотя обычно используют, я думаю, параметры среды: -Dselenide.baseUrl=http://123.23.23.1
        Configuration.baseUrl = "http://some.ru";
        //Далее
        open("/customer/orders");
        //Пример с авторизацией
        open("/", AuthenticationType.BASIC, new BasicAuthCredentials("", "user", "password"));

        //Навигация / вкладки
        Selenide.back();
        Selenide.refresh();
        Selenide.switchTo().window("The Internet");
        Selenide.closeWindow(); // close active tab
        Selenide.closeWebDriver(); // close browser completely. Также завершается сам по окончании теста / падении.

        //Frames
        Selenide.switchTo().frame("new");
        Selenide.switchTo().defaultContent(); // return from frame back to the main DOM

        //Алерты
        Selenide.confirm(); // OK in alert dialogs
        Selenide.dismiss(); // Cancel in alert dialogs

        //Куки / данные
        var cookie = new Cookie("foo", "bar"); //Add cookie
        WebDriverRunner.getWebDriver().manage().addCookie(cookie);
        Selenide.clearBrowserCookies();
        Selenide.clearBrowserLocalStorage();
        //и делаем рефреш, чтобы заработало

        //JS
        executeJavaScript("sessionStorage.clear();"); // no Selenide command for this yet

    }

    void selectors_examples() {

        $("div").click();                       //CSS
        element("div").click();
        element("div").click();
        $$("div").get(0).click();
        elements("div").get(0).click();

        $("div", 2).click();              //Элемент + позиция (отсчет от 0)

        $x("//h1/div").click();             //Xpath
        $(byXpath("//h1/div")).click();

        $(byText("full text")).click();        //Текст
        $(withText("ull tex")).click();

        $(byTagAndText("div", "full text"));  //Тег + текст
        $(withTagAndText("div", "ull text"));

        $("").parent();                     //родитель
        $("").sibling(1);             //следующий сосед (+индекс)
        $("").preceding(1);           //предшествующий (+индекс)
        $("").ancestor("div");      //дочерний, ближайший
        $("").closest("div");       //дочерний, ближайший

        $("div").$("h1").find(byText("abc")).click();   //последовательность
        $(byAttribute("abc", "x")).click(); //атрибут

        $(byId("mytext")).click();  //ID
        $("#mytext").click();
        $("#mytext").click();

        $(byClassName("red")).click();  //Class
        $(".red").click();
    }

    void actions_examples() {

        $("").click();
        $("").doubleClick();
        $("").contextClick();

        $("").hover();

        $("").setValue("text");
        $("").append("text");
        $("").clear();
        $("").setValue(""); // clear

        $("div").sendKeys("c"); // hotkey c on element
        actions().sendKeys("c").perform(); //hotkey c on whole application
        actions().sendKeys(Keys.chord(Keys.CONTROL, "f")).perform(); // Ctrl + F
        $("html").sendKeys(Keys.chord(Keys.CONTROL, "f"));

        $("").pressEnter();
        $("").pressEscape();
        $("").pressTab();


        // complex actions with keybord and mouse, example
        actions().moveToElement($("div")).clickAndHold().moveByOffset(300, 200).release().perform();

        // old html actions don't work with many modern frameworks
        $("").selectOption("dropdown_option");
        $("").selectRadio("radio_options");

    }

    void assertions_examples() {
        $("").shouldBe(visible);
        $("").shouldNotBe(visible);
        $("").shouldHave(text("abc"));
        $("").shouldNotHave(text("abc"));
        $("").should(appear);
        $("").shouldNot(appear);

        //longer timeouts
        $("").shouldBe(visible, Duration.ofSeconds(30));

    }

    void conditions_examples() {
        $("").shouldBe(visible);
        $("").shouldBe(hidden);

        $("").shouldHave(text("abc"));
        $("").shouldHave(exactText("abc"));
        $("").shouldHave(textCaseSensitive("abc"));
        $("").shouldHave(exactTextCaseSensitive("abc"));
        $("").should(matchText("[0-9]abc$"));

        $("").shouldHave(cssClass("red"));
        $("").shouldHave(cssValue("font-size", "12"));

        $("").shouldHave(value("25"));
        $("").shouldHave(exactValue("25"));
        $("").shouldBe(empty);

        $("").shouldHave(attribute("disabled"));
        $("").shouldHave(attribute("name", "example"));
        $("").shouldHave(attributeMatching("name", "[0-9]abc$"));

        $("").shouldBe(checked); // for checkboxes

        // Warning! Only checks if it is in DOM, not if it is visible! You don't need it in most tests!
        $("").should(exist);

        // Warning! Checks only the "disabled" attribute! Will not work with many modern frameworks
        $("").shouldBe(disabled);
        $("").shouldBe(enabled);
    }

    void collections_examples() {

        $$("div"); // does nothing!

        $$x("//div"); // by XPath

        // selections
        $$("div").filterBy(text("123")).shouldHave(size(1));
        $$("div").excludeWith(text("123")).shouldHave(size(1));

        $$("div").first().click();
        elements("div").first().click();
        // $("div").click();
        $$("div").last().click();
        $$("div").get(1).click(); // the second! (start with 0)
        $$("div").get(1).click(); // the second! (start with 0)
        $("div", 1).click(); // same as previous
        $$("div").findBy(text("123")).click(); //  finds first

        // assertions
        $$("").shouldHave(size(0));
        $$("").shouldHave(sizeGreaterThan(0));
        $$("").shouldHave(sizeGreaterThanOrEqual(1));
        $$("").shouldHave(sizeLessThan(3));
        $$("").shouldHave(sizeLessThanOrEqual(2));
        $$("").shouldBe(CollectionCondition.empty); // the same

        $$("").shouldHave(texts("Alfa", "Beta", "Gamma"));
        $$("").shouldHave(exactTexts("Alfa", "Beta", "Gamma"));
        $$("").shouldHave(textsInAnyOrder("Beta", "Gamma", "Alfa"));
        $$("").shouldHave(exactTextsCaseSensitiveInAnyOrder("Beta", "Gamma", "Alfa"));
        $$("").shouldHave(itemWithText("Gamma")); // only one text

    }

    void file_operation_examples() throws FileNotFoundException {

        File file1 = $("a.fileLink").download(); // only for <a href=".."> links
        File file2 = $("div").download(DownloadOptions.using(FileDownloadMode.FOLDER)); // more common options, but may have problems with Grid/Selenoid

        File file = new File("src/test/resources/readme.txt");
        $("#file-upload").uploadFile(file);
        $("#file-upload").uploadFromClasspath("readme.txt");
        // don't forget to submit!
        $("uploadButton").click();
    }

    void javascript_examples() {
        executeJavaScript("alert('selenide')");
        executeJavaScript("alert(arguments[0]+arguments[1])", "abc", 12);
        long fortytwo = executeJavaScript("return arguments[0]*arguments[1];", 6, 7);

    }

}
