package com.example.auto.ui.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


/**
 * Base page containing elements and methods from left menu and Google bar (on Inbox, Spam, etc pages).
 */
public class BasePage {

    @FindBy(className = "z0")
    protected WebElement buttonCompose;

    @FindBy(xpath="//a[@href='https://accounts.google.com/SignOutOptions?hl=en&continue=https://mail.google.com/" +
            "mail&service=mail']")
    private WebElement iconAccount;

    @FindBy(xpath="//a[text()='Sign out']")
    private WebElement buttonSignOut;

    @FindBy(className="ait")
    private WebElement expanderMoreLess;

    @FindBy(linkText = "Spam")
    private WebElement linkSpam;

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public NewMessagePopup initiateNewEmail() {
        buttonCompose.click();
        return new NewMessagePopup(driver);
    }

    public SignInPage signOut() {
        iconAccount.click();
        buttonSignOut.click();
        return new SignInPage(driver);
    }

    public SpamPage openSpamFolder() {
        expanderMoreLess.click();
        linkSpam.click();
        return new SpamPage(driver);
    }
}