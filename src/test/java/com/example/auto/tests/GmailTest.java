package com.example.auto.tests;

import com.example.auto.objects.Email;

import com.example.auto.factories.EmailStaticFactory;
import com.example.auto.ui.services.EmailManager;
import com.example.auto.ui.services.SignManager;
import com.example.auto.ui.services.SpamManager;

import com.example.auto.utils.StringUtils;

import org.junit.Test;
import org.junit.Before;

import java.util.concurrent.TimeUnit;
import org.junit.Assert;


/**
 * Gmail test - task 3. To implement following test scenario:
 * 1. Login as registered user1
 * 2. Send message to user2
 * 3. Login as registered user2
 * 4. Mark letter from user1 as "spam"
 * 5. Login user1
 * 6. Send letter to user2
 * 7. Login user2
 * 8. Go to folder: Spam"
 * 9. Check that the letter from user1 in Spam
 */

public class GmailTest extends BaseTest {
    private final String USERNAME1 = "testtasktask@gmail.com";
    // passwords are not correct here in purpose
    private final String PASSWORD1 = "pwd";
    private final String USERNAME2 = "testtasktask2@gmail.com";
    private final String PASSWORD2 = "pwd2";
    private final String EMAIL_TITLE = "Email title ";
    private final String MESSAGE = "Some awesome text ";

    public EmailManager emailMng;
    public SignManager signMng;
    public SpamManager spamMng;

    @Before
    public void initManagers() {
        emailMng = new EmailManager(customDriver);
        signMng = new SignManager(customDriver);
        spamMng = new SpamManager(customDriver);
    }

    @Test
    public void testSentGmail() {
        customDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        // 1, 2
        signMng.signInGmail(USERNAME1, PASSWORD1);

        String emailTitle = EMAIL_TITLE + StringUtils.getRandomString(6);
        Email email = EmailStaticFactory.createDefaultEmail(USERNAME2, emailTitle, MESSAGE
                                                            + StringUtils.getRandomString(7));

        emailMng.sendEmail(email);
        signMng.signoutGmail();

        // Accepting sign-out confirmation alert if shown (it's not always there, but sometimes).
        customDriver.acceptAlert();

        // 3, 4
        signMng.signInGmail(USERNAME2, PASSWORD2);
        spamMng.reportSpam();

        // 5, 6
        signMng.signoutGmail();
        customDriver.acceptAlert();
        signMng.signInGmail(USERNAME1, PASSWORD1);
        customDriver.acceptAlert();


        String secondTitle = EMAIL_TITLE + StringUtils.getRandomString(4);
        Email secondEmail = EmailStaticFactory.createDefaultEmail(USERNAME2, secondTitle, MESSAGE
                                                                  + StringUtils.getRandomString(8));
        emailMng.sendEmail(secondEmail);

        // 7, 8
        signMng.signoutGmail();
        customDriver.acceptAlert();
        signMng.signInGmail(USERNAME2, PASSWORD2);

        spamMng.navigateToSpamFolder();

        // Check that email title text is present on the list of emails on Spam folder.
        // Note: failed for me, as second email was not directed to Spam folder automatically.
        Assert.assertTrue("Verification Failed: email was not automatically directed to spam folder.",
                spamMng.getSpamListText().contains(secondTitle));
    }
}