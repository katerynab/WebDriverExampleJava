package com.example.auto.ui.services;

import com.example.auto.objects.Email;
import com.example.auto.ui.pages.NewMessagePopup;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

import com.example.auto.ui.pages.BasePage;

/**
 * Created by ekaterinabut on 11/12/15.
 */
public class EmailManager extends BaseManager {

    private final Logger logger = LogManager.getLogger(EmailManager.class.getName());

    private BasePage basePage;
    public EmailManager(WebDriver driver) {
        super(driver);
    }

    public void sendEmail(Email email) {
        logger.debug("Sent email to '" + email + "'");
        NewMessagePopup newMessagePopup = new BasePage(driver).initiateNewEmail();
        newMessagePopup.sendEmail(email);
        basePage = new BasePage(driver);
    }
}
