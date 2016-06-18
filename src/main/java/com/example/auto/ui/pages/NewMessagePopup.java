package com.example.auto.ui.pages;

import com.example.auto.objects.Email;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


/**
 * 'New message' pop-up elements and methods.
 */

public class NewMessagePopup {

    @FindBy(xpath="//textarea[@name='to']")
    private WebElement textareaTo;

    @FindBy(xpath="//input[@name='subjectbox']")
    private WebElement textareaSubjectbox;

    @FindBy(xpath="//div[@aria-label='Message Body']")
    private WebElement textareaMessageBody;

    @FindBy(xpath="//div[text()='Send']")
    private WebElement buttonSend;

    private WebDriver driver;

    public NewMessagePopup(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public BasePage sendEmail(Email email) {
        textareaTo.click();
        textareaTo.sendKeys(email.getAddressee());
        textareaSubjectbox.sendKeys(email.getSubject());
        textareaMessageBody.click();
        textareaMessageBody.sendKeys(email.getEmailBody());
        buttonSend.click();
        return new BasePage(driver);
    }
}
