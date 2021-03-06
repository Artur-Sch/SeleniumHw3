package rgsPages;

import core.BasePage;
import io.qameta.allure.Step;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RgsMainPage extends BasePage {
    public final static String RGS_URL = "https://rgs.ru";

    @FindBy(xpath = "//*[@id='main-navbar-collapse']/ol[1]/li[2]")
    private WebElement insuranceButton;

    @FindBy(xpath = "//*[@id='rgs-main-menu-insurance-dropdown']//*[contains(text(), 'ДМС')]")
    private WebElement dmsButton;


    @Step("Open DMS Page")
    public RgsDmsPage openDmsPage() {
        waitForReadyElement(insuranceButton).click();
        waitForReadyElement(dmsButton).click();
        return new RgsDmsPage();
    }

}
