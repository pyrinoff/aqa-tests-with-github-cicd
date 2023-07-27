package com.example.homeworks.demoqa.data;
import com.example.homeworks.util.RandomUtil;
import com.example.homeworks.util.DateUtil;

import com.github.javafaker.Faker;
import lombok.Getter;

import java.time.*;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

@Getter
public class HomeworkPageData {

    private final Faker faker = new Faker(new Locale("en"));

    private static final String[] GENDERS = {"Male", "Female"};
    private static final String[] SUBJECTS = {"English", "Chemistry"};
    private static final String[] MONTHS = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    private static final Map<String, String> STATES = new HashMap<>() {{
        put("Panipat", "Haryana");
        put("Karnal", "Haryana");
    }};

    private final String firstName = faker.name().firstName();
    private final String lastName = faker.name().lastName();
    private final String email = faker.internet().emailAddress();
    private final String mobile = faker.phoneNumber().subscriberNumber(10);
    private final String address = faker.address().fullAddress();
    private final String gender = RandomUtil.getRandomElement(GENDERS);
    private final String[] subjects = new String[]{RandomUtil.getRandomElement(SUBJECTS), RandomUtil.getRandomElement(SUBJECTS)};
    private final LocalDate birthdayDate = DateUtil.getLocalDate(faker.date().birthday(18, 45));
    private final String birthYear = String.valueOf(birthdayDate.getYear());
    private final String birthDay = String.valueOf(birthdayDate.getDayOfMonth());
    private final String birthMonth = MONTHS[birthdayDate.getMonthValue()];
    private final String birthCheck = birthDay + " " + birthMonth + "," + birthYear;
    private final String city = RandomUtil.getRandomKey(STATES);
    private final String state = STATES.get(this.city);
    private final String uploadFile = "src/test/resources/test.jpg";

}
