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
        open("/automation-practice-form");
        //hide the banner on the small screen to check if the test works
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");
        //
        $("#firstName").setValue("TestName");
        $("#lastName").setValue("TestLastName");
        $("#userEmail").setValue("mail@example.com");
        $("[for=gender-radio-1]").click();
        $("#userNumber").setValue("1234567890");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("August");
        $(".react-datepicker__year-select").selectOption("1983");
        $(".react-datepicker__day--025").click();
        $("#subjectsInput").setValue("Math").pressEnter();
        $("[for=hobbies-checkbox-2]").click();
        $("#currentAddress").scrollIntoView(false);
        $("#currentAddress").setValue("47 QA Street");
        $("#uploadPicture").uploadFromClasspath("test.jpeg");
        $(byText("Select State")).scrollIntoView(false);
        $(byText("Select State")).click();
        $(byText("NCR")).click();
        $(byText("Select City")).click();
        $(byText("Gurgaon")).click();
        $("#submit").click();
    }
}
