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


public class LoginSuccessfulPage {

    private final WebDriverWait webDriverWait;

    @FindBy(xpath = "//*[contains(@class,'post-title')]")
    private WebElement pageTitle;

    @FindBy(xpath = "//*[contains(@class,'wp-block-button__link') and text()='Log out']")
    private WebElement logOutButton;

    public void checkPageTitle(String title) {
        webDriverWait.until(ExpectedConditions.visibilityOf(pageTitle));
        assertThat(pageTitle.getText(),
                is(title));
    }

    public void clickLogoutButton() {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(logOutButton));
        logOutButton.click();
    }

    public LoginSuccessfulPage(WebDriver webDriver) {
        this.webDriverWait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        PageFactory.initElements(webDriver, this);
    }

}
