package com.mikkimesser;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class AutomationPracticeFormTests {

    @BeforeAll static void setUp() {
        Configuration.holdBrowserOpen = true;
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1280x720";
    }

    @Test
    void fillFormTest() {
        String firstName = "Harry";
        String lastName = "Dirty";
        String email = "mail@example.com";
        String phoneNumber = "1234567890";
        String year = "1983";
        String month = "August";
        String dt = "31";
        String address = "47 QA Street";
        String state = "NCR";
        String city = "Gurgaon";
        String pictureName = "test.jpeg";
        String subjShort = "Ma";
        String subjLong = "Maths";
        String gender = "Male";
        String hobby = "Reading";

        open("/automation-practice-form");
        //hide the banner on the small screen to check if the test works
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");

        //filling the fields
        $("#firstName").setValue(firstName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(email);
        $(byText(gender)).click();
        $("#userNumber").setValue(phoneNumber);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(month);
        $(".react-datepicker__year-select").selectOption(year);
        $(byAttribute("aria-label*",month+" "+dt.replaceFirst ("^0*", ""))).click();
        $("#subjectsInput").setValue(subjShort).pressEnter();
        $(byText(hobby)).click();
        $("#currentAddress").scrollIntoView(false);
        $("#currentAddress").setValue(address);
        $("#uploadPicture").uploadFromClasspath(pictureName);
        $(byText("Select State")).scrollIntoView(false);
        $(byText("Select State")).click();
        $(byText(state)).click();
        $(byText("Select City")).click();
        $(byText(city)).click();
        $("#submit").click();

        //Asserting the results table is shown
        $("#example-modal-sizes-title-lg").shouldBe(Condition.visible);
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));

        //Asserting the field values
        $(".table-responsive").shouldHave(text("Student Name"+" "+firstName+" "+lastName),
                text("Student Email"+" "+email),
                text("Gender"+" "+gender),
                text("Mobile"+" "+phoneNumber),
                text("Date of Birth"+" "+dt+" "+month+","+year),
                text("Subjects"+" "+subjLong),
                text("Hobbies"+" "+hobby),
                text("Picture"+" "+pictureName),
                text("Address"+ " "+address),
                text("State and City "+" "+state+" "+" "+city));

        //closing the form and asserting it closes
        $("#closeLargeModal").click();
        $("#example-modal-sizes-title-lg").shouldNotBe(Condition.visible);
    }
}
