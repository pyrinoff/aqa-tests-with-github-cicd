package com.example.homeworks.demoqa.test;

import com.example.homeworks.demoqa.TestBase;
import com.example.homeworks.demoqa.data.HomeworkPageData;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class HomeworkPageObjectTest extends TestBase {

    @Test
    @Owner("apyrinov")
    @Severity(SeverityLevel.NORMAL)
    @Feature("Selenide, allure, cicd")
    @Story("UI tests")
    @DisplayName("Тест с заполнением формы на demoqa")
    void registrationForm() {
        final HomeworkPageData data = new HomeworkPageData();

        registrationPage
                .openPage()
                .removeBanners()
                .setFirstName(data.getFirstName())
                .setLastName(data.getLastName())
                .setEmail(data.getEmail())
                .setPhone(data.getMobile())
                .setAddress(data.getAddress())
                .selectGender(data.getGender())
                .selectHobbySports()
                .selectBirthday(data.getBirthYear(), data.getBirthMonth(), data.getBirthDay())
                .setSubjects(data.getSubjects())
                .setState(data.getState())
                .setCity(data.getCity())
                .setUploadPicture(data.getUploadFile())
                .pressSubmit()
                .verifySubmittedValues("Student Name", data.getFirstName() + " " + data.getLastName())
                .verifySubmittedValues("Student Email", data.getEmail())
                .verifySubmittedValues("Gender", data.getGender())
                .verifySubmittedValues("Mobile", data.getMobile())
                .verifySubmittedValues("Date of Birth", data.getBirthCheck())
                .verifyOwnArrayOfText("Subjects", data.getSubjects())
                .verifySubmittedValues("Hobbies", "Sports")
                .verifySubmittedValues("Picture", "test.jpg")
                .verifySubmittedValues("Address", data.getAddress())
                .verifySubmittedValues("State and City", data.getState() + " " + data.getCity())
        ;
    }

}
