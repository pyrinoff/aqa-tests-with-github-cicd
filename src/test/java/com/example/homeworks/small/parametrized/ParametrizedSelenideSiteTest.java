package com.example.homeworks.small.parametrized;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.open;

/**
 * С русским IP страничка не загружается полностью.
 */

@Disabled
public class ParametrizedSelenideSiteTest {

    @BeforeAll
    static void beforeAll() {
        Configuration.baseUrl = "https://selenide.org";
        Configuration.timeout = 10000;
        Configuration.pageLoadTimeout = 60000;
    }

    static Stream<Arguments> checkButtonsByLang() {
        return Stream.of(
                Arguments.of(Language.EN, List.of("Quick start", "Docs", "FAQ", "Blog", "Javadoc", "Users", "Quotes")),
                Arguments.of(Language.RU, List.of("С чего начать?", "Док", "ЧАВО", "Блог", "Javadoc", "Пользователи", "Отзывы"))
        );
    }

    @ParameterizedTest(name = "Главная страница. Проверка наличия кнопок для языка {0} ({1})")
    @MethodSource
    void checkButtonsByLang(Language language, List<String> buttons) {
        open("/");
        $$("#languages a").find(Condition.text(language.name())).click();
        //$x("//div[@id='languages']/a[text()='"+language.name()+"']").click();
        $$(".main-menu-pages a").filter(Condition.visible).should(texts(buttons));
    }



}
