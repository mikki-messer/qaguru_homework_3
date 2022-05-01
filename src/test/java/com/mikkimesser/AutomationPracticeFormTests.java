package com.mikkimesser;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Configuration;
import com.github.javafaker.DateAndTime;
import com.github.javafaker.Faker;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class AutomationPracticeFormTests {

    Faker faker = new Faker();

    @BeforeAll static void setUp() {
        Configuration.holdBrowserOpen = true;
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.browserSize = "1280x720";
    }

    @Test
    void fillFormTest() throws ParseException {
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String email = faker.internet().emailAddress();
        String phoneNumber = faker.phoneNumber().subscriberNumber(10);

        //faking dates
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date randomDate = faker.date().between(simpleDateFormat.parse("1900-01-01"),
                simpleDateFormat.parse("2010-01-01"));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(randomDate);
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        String month = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
        String dt = String.format("%02d", calendar.get(Calendar.DAY_OF_MONTH));

        String address = faker.address().fullAddress();
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
        $("#genterWrapper").$(byText(gender)).click();

        $("#userNumber").setValue(phoneNumber);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(month);
        $(".react-datepicker__year-select").selectOption(year);
        $(".react-datepicker__day--0"+dt+":not(.react-datepicker__day--outside-month)").click();
        $("#subjectsInput").setValue(subjShort).pressEnter();
        $("#hobbiesWrapper").$(byText(hobby)).click();
        $("#currentAddress").scrollIntoView(false);
        $("#currentAddress").setValue(address);
        $("#uploadPicture").uploadFromClasspath(pictureName);
        $(byText("Select State")).scrollIntoView(false);
        $("#state").click();
        $("#stateCity-wrapper").$(byText(state)).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText(city)).click();
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
