package com.example.auto.ui.services;

import com.example.auto.ui.pages.BasePage;
import com.example.auto.ui.pages.SignInPage;
import com.example.auto.utils.ScreenshottingUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;

/**
 * Created by ekaterinabut on 11/13/15.
 */
public class SignManager extends BaseManager {

    private final Logger logger = LogManager.getLogger(SignManager.class.getName());

    private SignInPage signinPage;
    private BasePage basePage;
    private ScreenshottingUtils screenshottingUtils;
    public SignManager(WebDriver driver) {
        super(driver);
    }

    public void signInGmail(String email, String password)
    {
        logger.debug("Sign-in to '" + email + "' account");
        signinPage = new SignInPage(driver);
        signinPage.signIn(email, password);
        // This scr will show what's inside Spam folder on that step.
//        screenshottingUtils.highlightElement(driver, linkSpam);
        screenshottingUtils.takeScreenshot(driver);
    }
    public void signoutGmail()
    {
        logger.debug("Signing out from gmail.");
        basePage = new BasePage(driver);
        basePage.signOut();
    }
}