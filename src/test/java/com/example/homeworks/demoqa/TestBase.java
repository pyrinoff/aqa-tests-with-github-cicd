package com.example.homeworks.demoqa;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.example.homeworks.demoqa.page.AutomationPracticeFormPage;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.BeforeAll;

public class TestBase {

    public final static AutomationPracticeFormPage registrationPage = new AutomationPracticeFormPage();

    @BeforeAll
    static void beforeAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
        Configuration.baseUrl="https://demoqa.com";
        Configuration.browserSize="1920x1080";
        Configuration.pageLoadTimeout=60000;
    }

}
