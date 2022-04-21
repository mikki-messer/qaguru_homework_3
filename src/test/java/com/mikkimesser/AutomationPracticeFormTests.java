package com.mikkimesser;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.commands.ScrollTo;
import com.codeborne.selenide.selector.ByText;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

import java.io.File;

import static com.codeborne.selenide.Selectors.byText;
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
        String dt = "25";
        String address = "47 QA Street";
        String state = "NCR";
        String city = "Gurgaon";
        String pictureName = "test.jpeg";

        open("/automation-practice-form");
        //hide the banner on the small screen to check if the test works
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");
        //
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $("[for=gender-radio-1]").click();
        $("#userNumber").setValue(phoneNumber);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("August");
        $(".react-datepicker__year-select").selectOption("1983");
        $(".react-datepicker__day--025").click();
        $("#subjectsInput").setValue("Math").pressEnter();
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
