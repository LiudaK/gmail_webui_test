import pageobject.EmailHomePage;
import pageobject.EmailViewPage;
import webUtil.WebUtil;
import pageobject.SignInPage;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Created by lkarabelnikava on 11/19/2016.
 */
public class GmailSigninTest {
    public GmailSigninTest() {
        System.setProperty("webdriver.gecko.driver", "C:\\Selenium\\Firefox Driver\\geckodriver.exe");
        driver = new FirefoxDriver();
    }

    public WebDriver driver; // = new FirefoxDriver();

    @Test
    public void gmailLogingSouldBeSuccesful(){
        WebDriverWait wait = new WebDriverWait(driver, 30);
//1. go to Gmail website
        SignInPage signInPage = WebUtil.goToSignInPage(driver);

//2. Fill in username
        // implicit wait
        signInPage.fillInUserName(driver,"luda4utest@gmail.com");

//3. fill in password
        signInPage.fillInPassword(driver, "filipenko100");

//4. click sign in
       EmailHomePage emailHomePage = signInPage.clickSignIn(driver);

 //5. verify user did sign in

        //is element present
        Assert.assertTrue("inbox should exist", driver.findElements(By.partialLinkText("Inbox")).size()>0);
//6. sign out
        signInPage = emailHomePage.signOut(driver);


//7. verify user did fign out

        Assert.assertTrue("signIn button should exisy", emailHomePage.isElementExist(driver));

        //Assert.assertTrue("signIn button should exisy", driver.findElements(By.id("Email")).size()>0);
    }

    @Test
    public void gmailSendAndReciveTest(){
        //1. go to Gmail website
        SignInPage signInPage = WebUtil.goToSignInPage(driver);

//2. Fill in username
        // implicit wait
        signInPage.fillInUserName(driver,"luda4utest@gmail.com");

//3. fill in password
        signInPage.fillInPassword(driver, "filipenko100");

//4. click sign in
        EmailHomePage emailHomePage = signInPage.clickSignIn(driver);

        //click Compose
        emailHomePage.clickComposeButton(driver);

        //fill in recipient
        emailHomePage.fillInRecipient(driver,"luda4utest@gmail.com" );

        //fill in a subject
        final String subject ="Gmail Sent Email Test";
        emailHomePage.fillInSubject(driver, subject );

        //fill in a body
        final String body = "Hello.Good morning";
        emailHomePage.fillInBody(driver, body);
        WebElement bodyTextArea = driver.findElement(By.cssSelector("div[aria-label='Message Body'"));

        bodyTextArea.clear();
        bodyTextArea.sendKeys(body);
        //6.  click send
        emailHomePage.clickSend(driver);

        //7. click inbox again
        emailHomePage.clickInboxWithNewEmail(driver);

        //8. click email
        EmailViewPage emailViewPage = emailHomePage.clickNewEmail(driver);

        //9. verify the email subject and body is correct
        String actualSubject = emailViewPage.getEmailSubjectText(driver);

        Assert.assertEquals("Subject should be the same", subject, actualSubject);
        String actualBody = emailViewPage.getEmailBodyText(driver);

        Assert.assertEquals("Email Body text should be the same", body ,actualBody);
        //10. sign out
        emailHomePage.signOut(driver);

    }
    @After
    public void tearDown(){
        driver.quit();
    }
}
