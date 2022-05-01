package com.mikkimesser.pages;

import com.codeborne.selenide.Condition;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class RegistrationFormPage {
    //locators


    //actions
    public void openPage(){
        open("/automation-practice-form");
        $(".practice-form-wrapper").shouldHave(text("Student Registration Form"));
        //hide the banner on the small screen to check if the test works
        executeJavaScript("$('footer').remove()");
        executeJavaScript("$('#fixedban').remove()");
    }

    public void setFirstName(String _firstName){
        $("#firstName").setValue(_firstName);
    }

    public void setLastName(String _lastName){
        $("#lastName").setValue(_lastName);
    }

    public void setEmail(String _email){
        $("#userEmail").setValue(_email);
    }

    public  void setGender(String _gender) {
        $("#genterWrapper").$(byText(_gender)).click();
    }

    public void setPhoneNumber(String _phoneNumber){
        $("#userNumber").setValue(_phoneNumber);
    }

    public void setBirthDayDate(String _year, String _month, String _day){
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(_month);
        $(".react-datepicker__year-select").selectOption(_year);
        $(".react-datepicker__day--0"+_day+":not(.react-datepicker__day--outside-month)").click();

    }

    public  void setSubject(String _subject){
        $("#subjectsInput").setValue(_subject).pressEnter();
    }

    public  void setHobby(String _hobby) {
        $("#hobbiesWrapper").$(byText(_hobby)).click();
    }

    public void setCurrentAddress(String _address){
        $("#currentAddress").scrollIntoView(false);
        $("#currentAddress").setValue(_address);
    }

    public void uploadPicture(String _picturePath){
        $("#uploadPicture").uploadFromClasspath(_picturePath);
    }

    public void selectState(String _state) {
        $(byText("Select State")).scrollIntoView(false);
        $("#state").click();
        $("#stateCity-wrapper").$(byText(_state)).click();
    }

    public  void selectCity(String _city){
        $("#city").click();
        $("#stateCity-wrapper").$(byText(_city)).click();
    }

    public  void submitForm(){
        $("#submit").click();
    }
    public void checkResultsTableShown() {
        $("#example-modal-sizes-title-lg").shouldBe(Condition.visible);
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
    }
    public void checkResult(String key, String value)
    {
        $(".table-responsive").$(byText(key))
                .parent().shouldHave(text(value));
    }
    public void closeResultsTable(){
        $("#closeLargeModal").click();
        $("#example-modal-sizes-title-lg").shouldNotBe(Condition.visible);
    }
}
