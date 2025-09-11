package tests;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pages.AutomationPracticeFormPage;

public class RegistrationWithPageObjects {

    AutomationPracticeFormPage formPage = new AutomationPracticeFormPage();

    @BeforeAll
    static void beforeAll() {
        Configuration.browserSize = "1920x1080";
        Configuration.baseUrl = "https://demoqa.com";
        Configuration.pageLoadStrategy = "eager";
    }

    @Test
    void fillFormFullDataTest() {
        formPage.openPage()
                .setFirstName("Tom")
                .setLastName("Jerry")
                .setEmail("TomJerry@disney.com")
                .selectGender("Male")
                .setPhone("1234567890")
                .setDateOfBirth("9", "August", "1999")
                .setSubject("Economics")
                .selectHobby("Sports")
                .uploadPicture("1234.png")
                .setAddress("Home")
                .selectState("Haryana")
                .selectCity("Panipat")
                .submit();

        formPage.resultsTable().checkResult("Student Name", "Tom Jerry");
        formPage.resultsTable().checkResult("Student Email", "TomJerry@disney.com");
        formPage.resultsTable().checkResult("Gender", "Male");
        formPage.resultsTable().checkResult("Mobile", "1234567890");
        formPage.resultsTable().checkResult("Date of Birth", "9 August,1999");
        formPage.resultsTable().checkResult("Subjects", "Economics");
        formPage.resultsTable().checkResult("Hobbies", "Sports");
        formPage.resultsTable().checkResult("Picture", "1234.png");
        formPage.resultsTable().checkResult("Address", "Home");
        formPage.resultsTable().checkResult("State and City", "Haryana Panipat");
    }

    @Test
    void fillFormMinimumDataTest() {
        formPage.openPage()
                .setFirstName("OnlyName")
                .setLastName("OnlyLast")
                .selectGender("Male")
                .setPhone("9876543210")
                .submit();

        formPage.resultsTable().checkResult("Student Name", "OnlyName OnlyLast");
        formPage.resultsTable().checkResult("Gender", "Male");
        formPage.resultsTable().checkResult("Mobile", "9876543210");
    }

    @Test
    void negativeInvalidEmailTest() {
        formPage.openPage()
                .setFirstName("Bad")
                .setLastName("Email")
                .setEmail("invalidEmail")
                .selectGender("Male")
                .setPhone("1112223333")
                .submit();

        formPage.resultsTable().checkResult("Student Name", "Bad Email");

    }
}
