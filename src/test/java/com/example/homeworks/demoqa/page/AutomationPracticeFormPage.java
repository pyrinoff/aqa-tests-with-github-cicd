package com.example.homeworks.demoqa.page;

import com.codeborne.selenide.SelenideElement;
import com.example.homeworks.demoqa.component.DatePicker;
import io.qameta.allure.Step;

import java.io.File;
import java.util.Arrays;

import static com.codeborne.selenide.Condition.ownText;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class AutomationPracticeFormPage {

    private static final SelenideElement
            firstNameInput = $("#firstName"),
            lastNameInput = $("#lastName"),
            emailInput = $("#userEmail"),
            genderMaleRadio = $("label[for='gender-radio-1']"),
            phoneInput = $("#userNumber"),
            dateOfBirthField = $("#dateOfBirthInput"),
            subjectsField = $("#subjectsInput"),
            hobbySportsCheckbox = $("label[for='hobbies-checkbox-1']"),
            pictureFile = $("#uploadPicture"),
            addressInput = $("#currentAddress"),
            stateInputSelect = $("#state"),
            cityInputSelect = $("#city"),
            submitButton = $("#submit");

    public static SelenideElement getStateSelector(final String state) {
        return $x("//div[@id='state']//div[text()='" + state + "']");
    }

    public static SelenideElement getCitySelector(final String city) {
        return $x("//div[@id='city']//div[text()='" + city + "']");
    }

    public static SelenideElement getTableSelector(final String name) {
        return $x("//td[text()='" + name + "']/following-sibling::td[1]");
    }

    public static SelenideElement getGenderRadio(final String gender) {
        return $("#genterWrapper").find(byText(gender));
    }


    @Step("Открытие страницы")
    public AutomationPracticeFormPage openPage() {
        open("/automation-practice-form");
        return this;
    }

    @Step
    public AutomationPracticeFormPage setFirstName(final String value) {
        firstNameInput.setValue(value);
        return this;
    }

    @Step
    public AutomationPracticeFormPage setLastName(final String value) {
        lastNameInput.setValue(value);
        return this;
    }

    @Step
    public AutomationPracticeFormPage setEmail(final String value) {
        emailInput.setValue(value);
        return this;
    }

    @Step
    public AutomationPracticeFormPage setPhone(final String value) {
        phoneInput.setValue(value);
        return this;
    }

    @Step
    public AutomationPracticeFormPage setAddress(final String value) {
        addressInput.setValue(value);
        return this;
    }

    @Step
    public AutomationPracticeFormPage selectGender(final String gender) {
        getGenderRadio(gender).click();
        return this;
    }

    @Step
    public AutomationPracticeFormPage selectHobbySports() {
        hobbySportsCheckbox.click();
        return this;
    }

    @Step
    public AutomationPracticeFormPage pressSubmit() {
        submitButton.click();
        return this;
    }

    @Step
    public AutomationPracticeFormPage selectBirthday(final String birthYear, final String birthMonth, final String birthDay) {
        dateOfBirthField.click();
        //тут клик нужен после двух селектов??
        DatePicker.setDate(birthYear, birthMonth, birthDay);
        return this;
    }

    @Step
    public AutomationPracticeFormPage setSubjects(final String[] subjects) {
        for (final String oneSubject : subjects) {
            subjectsField.setValue(oneSubject).pressEnter(); //альтернатива - ввели текст и нажали Enter
            //$("#subjectsInput").sendKeys(oneSubject); //отправили сочетание
            //$x("//div[@id='subjectsContainer']//div[contains(@class, 'subjects-auto-complete__option') and text()='"+oneSubject+"']").click(); //кликнули подсказку
        }
        return this;
    }

    @Step
    public AutomationPracticeFormPage setState(final String state) {
        stateInputSelect.click();
        getStateSelector(state).click();
        return this;
    }

    @Step
    public AutomationPracticeFormPage setCity(final String city) {
        cityInputSelect.click();
        getCitySelector(city).click();
        return this;
    }

    @Step
    public AutomationPracticeFormPage setUploadPicture(final String filepath) {
        pictureFile.uploadFile(new File(filepath));
        return this;
    }

    @Step
    public AutomationPracticeFormPage verifySubmittedValues(final String name, final String value) {
        getTableSelector(name).should(text(value));
        return this;
    }

    @Step
    public AutomationPracticeFormPage verifyOwnArrayOfText(final String fieldName, final String[] stringsShouldContains) {
        final SelenideElement tableSelector = getTableSelector(fieldName);
        Arrays.stream(stringsShouldContains).toList().forEach(oneSubject -> tableSelector.should(ownText(oneSubject)));
        return this;
    }

    @Step("Удаление баннеров")
    public AutomationPracticeFormPage removeBanners() {
        executeJavaScript("$('#fixedban').remove()");
        executeJavaScript("$('footer').remove()");
        return this;
    }


}
