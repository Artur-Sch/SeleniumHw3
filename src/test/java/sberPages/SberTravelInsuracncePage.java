package sberPages;

import core.BasePage;
import core.User;
import io.qameta.allure.Step;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SberTravelInsuracncePage extends BasePage {

    @FindBy(xpath = "//*[contains(text(),'Минимальная')]")
    WebElement minimalButton;

    @FindBy(xpath = "//*[@ng-click='save()']")
    WebElement buttonNextForm;

    @FindBy(xpath = "//*[@ng-click='save()']")
    WebElement saveButton;

    @FindBy(xpath = "//div[contains(text(), 'Заполнены')]")
    WebElement errorElement;

    @FindBy(xpath = "//*[@name='insured0_birthDate']")
    WebElement insuredBirthDate;

    @FindBy(xpath = "//*[@name='birthDate']")
    WebElement birthDate;

    @FindBy(xpath = "//*[@name='issueDate']")
    WebElement issueDate;

    @FindBy(xpath = "//h4[contains(text(),'Пол')]/following::span[1]")
    WebElement manSex;

    @FindBy(xpath = "//h4[contains(text(),'Пол')]/following::span[2]")
    WebElement womanSex;

    @FindBy(xpath = "//*[@name='passport_number']")
    WebElement passportNumber;

    @FindBy(xpath = "//*[@name='issuePlace']")
    WebElement issuePlace;

    @Step
    public void setMinimalClickAndNext() {
        waitForReadyElement(minimalButton).click();
        waitForReadyElement(buttonNextForm).click();
    }

    @Step
    public void fillInTheFormInsuredUser(User user) {
        fillInputByName("Фамилия /Surname", user.getLastNameLat(), "[@name='insured0_surname']");
        fillInputByName("Имя",  user.getFirstNameLat(), "[@name='insured0_name']");
        waitForReadyElement(insuredBirthDate).sendKeys(user.getBirthDate()+ Keys.TAB);
        checkErrorWithAttribute(insuredBirthDate, user.getBirthDate());
    }

    @Step
    public void fillInTheFormInsurantUser(User user){
        fillInputByName("Фамилия", user.getLastName(), "[@name='surname']");
        fillInputByName("Имя", user.getFirstName(), "[@name='name']");
        fillInputByName("Отчество", user.getPatronymic(), "");
        if (user.getSex().contains("м")) {
            waitForReadyElement(manSex).click();
        } else waitForReadyElement(womanSex).click();

        fillInputByName("Серия и номер паспорта", user.getPassportSeries(), "[@name='passport_series']");
        waitForReadyElement(passportNumber).sendKeys(user.getPassportNumber());
        checkErrorWithAttribute(passportNumber, user.getPassportNumber());

        waitForReadyElement(issuePlace).sendKeys(user.getPassportIssuePlace());
        checkErrorWithAttribute(issuePlace, user.getPassportIssuePlace());
        waitForReadyElement(issueDate).sendKeys(user.getPassportIssueDate()+ Keys.TAB);
        waitForReadyElement(birthDate).sendKeys(user.getBirthDate()+ Keys.TAB);
        checkErrorWithAttribute(issueDate, user.getPassportIssueDate());
        checkErrorWithAttribute(birthDate, user.getBirthDate());
    }

    @Step
    public void sendForm() {
        waitForReadyElement(saveButton).click();
    }

    @Step
    public void checkMessage() {
        checkTextAvailabilityFromElement(errorElement, "Заполнены не все обязательные поля");
    }



}
