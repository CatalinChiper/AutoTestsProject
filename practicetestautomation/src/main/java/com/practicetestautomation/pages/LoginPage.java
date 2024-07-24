package com.practicetestautomation.pages;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class LoginPage {

    private final WebDriverWait webDriverWait;

    @FindBy(xpath = "//input[@id='username']")
    public WebElement userNameInput;

    @FindBy(xpath = "//input[@id='password']")
    public WebElement passwordInput;

    @FindBy(xpath = "//button[@id='submit']")
    public WebElement submitButton;

    @FindBy(xpath = "//*[@id='error']")
    public WebElement validationMessage;

    public LoginPage(WebDriver webDriver) {
        this.webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        PageFactory.initElements(webDriver, this);
    }

    public static LoginPage using(WebDriver webDriver) {
        return new LoginPage(webDriver);
    }

    public LoginPage setUserName(String userName) {
        webDriverWait.until(ExpectedConditions.visibilityOf(userNameInput));
        userNameInput.clear();
        userNameInput.sendKeys(userName);
        return this;
    }

    public LoginPage setPassword(String password) {
        webDriverWait.until(ExpectedConditions.visibilityOf(passwordInput));
        passwordInput.clear();
        passwordInput.sendKeys(password);
        return this;
    }

    public void clickSubmitButton() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(submitButton));
        submitButton.click();
    }

    public void checkValidationMessageHasText(String errorMessage) {
        webDriverWait.until(ExpectedConditions.visibilityOf(validationMessage));
        assertThat(validationMessage.getText(),
                is(errorMessage));
    }

    public static void openPageAndLoginWithCredentials(WebDriver driver, String user, String pwd) {
        LoginPage.using(driver)
                .setUserName(user)
                .setPassword(pwd)
                .clickSubmitButton();
    }
}
