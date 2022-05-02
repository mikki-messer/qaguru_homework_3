package com.mikkimesser.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import com.mikkimesser.pages.components.CalendarComponent;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationFormPage {
    //sharedComponents
    CalendarComponent calendarComponent = new CalendarComponent();
    //locators
    SelenideElement registrationFormDiv = $(".practice-form-wrapper");
    SelenideElement firstNameInput = $("#firstName");
    SelenideElement lastNameInput = $("#lastName");
    SelenideElement emailInput = $("#userEmail");
    SelenideElement genderDiv = $("#genterWrapper");
    SelenideElement phoneNumber = $("#userNumber");
    SelenideElement dateOfBirthInput = $("#dateOfBirthInput");
    SelenideElement subjectsInput = $("#subjectsInput");
    SelenideElement hobbiesDiv = $("#hobbiesWrapper");
    SelenideElement addressInput = $("#currentAddress");
    SelenideElement uploadPictureInput = $("#uploadPicture");
    SelenideElement stateAndCityDiv = $("#stateCity-wrapper");
    SelenideElement stateInput = $("#state");
    SelenideElement cityInput = $("#city");
    SelenideElement submitButton = $("#submit");
    SelenideElement modalWindowDiv = $("#example-modal-sizes-title-lg");
    SelenideElement resultsTable = $(".table-responsive");
    SelenideElement closeModalWindowButton = $("#closeLargeModal");
    String registrationFormHeader = "Student Registration Form";
    String resultsFormHeader = "Thanks for submitting the form";
    //actions
    public void openPage(){
        open("/automation-practice-form");
        registrationFormDiv.shouldHave(text(registrationFormHeader));
        //hide the banner on the small screen to check if the test works
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");
    }

    public void setFirstName(String _firstName){
        firstNameInput.setValue(_firstName);
    }

    public void setLastName(String _lastName){
        lastNameInput.setValue(_lastName);
    }

    public void setEmail(String _email){
        emailInput.setValue(_email);
    }

    public  void setGender(String _gender) {
        genderDiv.$(byText(_gender)).click();
    }

    public void setPhoneNumber(String _phoneNumber){
        phoneNumber.setValue(_phoneNumber);
    }

    public void setBirthDayDate(String _day, String _month, String _year ){
        dateOfBirthInput.click();
        calendarComponent.setDate(_day, _month, _year);
    }

    public  void setSubject(String _subject){
        subjectsInput.setValue(_subject).pressEnter();
    }

    public  void setHobby(String _hobby) {
        hobbiesDiv.$(byText(_hobby)).click();
    }

    public void setCurrentAddress(String _address){
        addressInput.scrollIntoView(false);
        addressInput.setValue(_address);
    }

    public void uploadPicture(String _picturePath){
        uploadPictureInput.uploadFromClasspath(_picturePath);
    }

    public void selectState(String _state) {
        stateInput.scrollIntoView(false);
        stateInput.click();
        stateAndCityDiv.$(byText(_state)).click();
    }

    public  void selectCity(String _city){
        cityInput.click();
        stateAndCityDiv.$(byText(_city)).click();
    }

    public  void submitForm(){
        submitButton.click();
    }
    public void checkResultsTableShown() {
        modalWindowDiv.shouldBe(Condition.visible);
        modalWindowDiv.shouldHave(text(resultsFormHeader));
    }
    public void checkResult(String key, String value)
    {
        resultsTable.$(byText(key))
                .parent().shouldHave(text(value));
    }
    public void closeResultsTable(){
        closeModalWindowButton.click();
        modalWindowDiv.shouldNotBe(Condition.visible);
    }
}
