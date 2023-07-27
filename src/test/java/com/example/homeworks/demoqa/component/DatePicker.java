package com.example.homeworks.demoqa.component;

import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$x;

public class DatePicker {

    private static final SelenideElement
            yearSelector = $(".react-datepicker__year-select"),
            monthSelector = $(".react-datepicker__month-select");

    private static SelenideElement getDaySelector(final String birthMonth, String birthDay) {
        //return $x("//div[contains(@class, 'react-datepicker__day') and contains(@aria-label, '" + birthMonth + "') and text()='" + birthDay + "']");
        //return $(".react-datepicker__day--0"+birthDay+":not(.react-datepicker__day--outside-month)");
        if(birthDay.length() == 1) birthDay = "0" + birthDay;
        return $x("//div[contains(@class, 'react-datepicker__day--0"+birthDay+"') and not(contains(@class, 'react-datepicker__day--outside-month'))]");
    }

    public static void setDate(final String year, final String month, final String day) {
        yearSelector.selectOptionByValue(year);
        monthSelector.selectOption(month);
        getDaySelector(month, day).click();
    }


}
