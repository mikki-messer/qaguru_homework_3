package com.mikkimesser;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AutomationPracticeFormTests {

    @BeforeAll static void setUp() {
        Configuration.holdBrowserOpen = true;
        Configuration.baseUrl = "https://demoqa.com";
        //Configuration.browserSize = "1280x720)";

    }
    @Test
    void fillFormTest() {
        open("/automation-practice-form");
        $("#firstName").setValue("TestName");
    }
}
