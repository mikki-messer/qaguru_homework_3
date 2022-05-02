package com.mikkimesser.tests;

import com.codeborne.selenide.Configuration;
import com.github.javafaker.Faker;
import com.mikkimesser.pages.RegistrationFormPage;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


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
        String address = faker.address().fullAddress();

        //faking dates
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date randomDate = faker.date().between(simpleDateFormat.parse("1900-01-01"),
                simpleDateFormat.parse("2010-01-01"));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(randomDate);
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        String month = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
        String dt = String.format("%02d", calendar.get(Calendar.DAY_OF_MONTH));

        String state = "NCR";
        String city = "Gurgaon";
        String pictureName = "test.jpeg";
        String subject = "Maths";
        String gender = "Male";
        String hobby = "Reading";

        RegistrationFormPage registrationFormPage = new RegistrationFormPage();
        registrationFormPage.openPage();

        //filling the fields
        registrationFormPage.setFirstName(firstName);
        registrationFormPage.setLastName(lastName);
        registrationFormPage.setEmail(email);
        registrationFormPage.setGender(gender);
        registrationFormPage.setPhoneNumber(phoneNumber);

        registrationFormPage.setBirthDayDate(year, month, dt);

        registrationFormPage.setSubject(subject.substring(0,2));
        registrationFormPage.setHobby(hobby);
        registrationFormPage.setCurrentAddress(address);
        registrationFormPage.uploadPicture(pictureName);
        registrationFormPage.selectState(state);
        registrationFormPage.selectCity(city);
        registrationFormPage.submitForm();

        //Asserting the results table is shown
        registrationFormPage.checkResultsTableShown();

        //Asserting the field values
        registrationFormPage.checkResult("Student Name", firstName+" "+lastName);
        registrationFormPage.checkResult("Student Email", email);
        registrationFormPage.checkResult("Mobile", phoneNumber);
        registrationFormPage.checkResult("Date of Birth", dt+" "+month+","+year);
        registrationFormPage.checkResult("Subjects", subject);
        registrationFormPage.checkResult("Hobbies", hobby);
        registrationFormPage.checkResult("Picture", pictureName);
        registrationFormPage.checkResult("Address", address);
        registrationFormPage.checkResult("State and City", state+" "+city);

        //closing the form and asserting it closes
        registrationFormPage.closeResultsTable();
    }
}
