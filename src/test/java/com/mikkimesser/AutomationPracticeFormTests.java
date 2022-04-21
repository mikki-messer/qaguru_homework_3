package com.mikkimesser;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.codeborne.selenide.commands.ScrollTo;
import com.codeborne.selenide.selector.ByText;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.io.File;

import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.*;

public class AutomationPracticeFormTests {

    @BeforeAll static void setUp() {
        Configuration.holdBrowserOpen = true;
        Configuration.baseUrl = "https://demoqa.com";
    }
    @Test
    void fillFormTest() {
        String firstName = "Harry";
        String lastName = "Dirty";
        String email = "mail@example.com";
        String phoneNumber = "1234567890";
        String year = "1983";
        String month = "August";
        String dt = "02";
        String address = "47 QA Street";
        String state = "NCR";
        String city = "Gurgaon";
        String pictureName = "test.jpeg";
        String subjShort = "Ma";
        String subjLong = "Maths";

        open("/automation-practice-form");
        //hide the banner on the small screen to check if the test works
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");
        //
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $(byText("Male")).click();
        $("#userNumber").setValue(phoneNumber);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(month);
        $(".react-datepicker__year-select").selectOption(year);

        ElementsCollection days = $$(".react-datepicker__day--0"+dt);
        days.excludeWith(Condition.cssClass("react-datepicker__day--outside-month")).get(0).click();

        $("#subjectsInput").setValue(subjShort).pressEnter();
        $("[for=hobbies-checkbox-2]").click();
        $("#currentAddress").scrollIntoView(false);
        $("#currentAddress").setValue(address);
        $("#uploadPicture").uploadFromClasspath(pictureName);
        $(byText("Select State")).scrollIntoView(false);
        $(byText("Select State")).click();
        $(byText(state)).click();
        $(byText("Select City")).click();
        $(byText(city)).click();
        $("#submit").click();
    }
}
