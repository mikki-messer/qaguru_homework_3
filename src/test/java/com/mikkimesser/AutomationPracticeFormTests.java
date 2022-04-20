package com.mikkimesser;

import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.open;

public class AutomationPracticeFormTests {

    @Test
    void fillFormTest()
    {
        open("https://demoqa.com/automation-practice-form");
    }
}
