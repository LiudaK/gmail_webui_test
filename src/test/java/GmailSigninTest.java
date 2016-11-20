import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
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


        driver.get("http://gmail.com");
        //implicit wait
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        WebElement usernameTextbox = driver.findElement(By.id("Email"));
        usernameTextbox.clear();
        usernameTextbox.sendKeys("luda4utest@gmail.com");
        driver.findElement(By.id("next")).click();
        //synchronization, Explicit wait
        WebDriverWait wait = new WebDriverWait(driver, 30);

        WebElement checkbox = driver.findElement(By.id("PersistentCookie"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("PersistentCookie")));
      checkbox.click();

        WebElement passwordTextbox = driver.findElement(By.id("Passwd"));
        passwordTextbox.clear();
        passwordTextbox.sendKeys("filipenko100");
        WebElement signInButton = driver.findElement(By.id("signIn"));
        signInButton.click();
        driver.findElement(By.partialLinkText("Inbox"));

       //wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Inbox")));
        //is element present
        Assert.assertTrue("inbox should exist", driver.findElements(By.partialLinkText("Inbox")).size()>0);
        //sign out
        WebElement profileButton = driver.findElement(By.cssSelector(".gb_8a.gbii"));
        profileButton.click();
        WebElement signoutLinkage = driver.findElement(By.id("gb_71"));
        signoutLinkage.click();
        //verify user did fign out
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Email")));
        Assert.assertTrue("signIn button should exisy", driver.findElements(By.id("Email")).size()>0);
    }

    @Test
    public void gmailSendAndReciveTest(){
        driver.get("http://gmail.com");
        //implicit wait
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        WebElement usernameTextbox = driver.findElement(By.id("Email"));
        usernameTextbox.clear();
        usernameTextbox.sendKeys("luda4utest@gmail.com");
        driver.findElement(By.id("next")).click();
        //synchronization, Explicit wait
        WebDriverWait wait = new WebDriverWait(driver, 40);

        WebElement checkbox = driver.findElement(By.id("PersistentCookie"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("PersistentCookie")));
        checkbox.click();

        WebElement passwordTextbox = driver.findElement(By.id("Passwd"));
        passwordTextbox.clear();
        passwordTextbox.sendKeys("filipenko100");
        WebElement signInButton = driver.findElement(By.id("signIn"));
        signInButton.click();
        //click Compose
        WebElement composeButton = driver.findElement(By.cssSelector("div[role='button'][gh='cm']"));
        composeButton.click();
        WebElement toTextArea = driver.findElement(By.cssSelector("textarea[name='to']"));
        toTextArea.clear();
        toTextArea.sendKeys("luda4utest@gmail.com");
        //fill in a subject
        WebElement subjectTextArea = driver.findElement(By.cssSelector("input[placeholder='Subject']"));
        final String subject ="Gmail Sent Email Test";
        subjectTextArea.clear();
        subjectTextArea.sendKeys(subject);
        //fill in a body
        WebElement bodyTextArea = driver.findElement(By.cssSelector("div[aria-label='Message Body'"));
        final String body = "Hello.Good morning";
        bodyTextArea.clear();
        bodyTextArea.sendKeys(body);
        // click send
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@aria-label,\"Send\")]")));
        WebElement sendButton = driver.findElement(By.xpath("//div[contains(@aria-label,\"Send\")]"));
        sendButton.click();
        //click inbox again
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@aria-label, 'Inbox')]")));
        WebElement inboxLinkage = driver.findElement(By.xpath("//a[contains(@aria-label, 'Inbox')]"));
        inboxLinkage.click();
        //click email
        WebElement newEmail = driver.findElement(By.cssSelector("div[class='y6'] span[id] b"));
        newEmail.click();
        //verify the email subject and body is correct
        WebElement subjectArea = driver.findElement(By.cssSelector("h2[class='hP']"));
        Assert.assertEquals("Subject should be the same", subject, subjectArea.getText());
        WebElement bodyArea = driver.findElement(By.cssSelector("div[class='nH hx'] div[dir='ltr']"));
        Assert.assertEquals("Email Body text should be the same", body ,bodyArea.getText());
        //sign out
        WebElement profileButton = driver.findElement(By.cssSelector(".gb_8a.gbii"));
        profileButton.click();
        WebElement signoutLinkage = driver.findElement(By.id("gb_71"));
        signoutLinkage.click();
    }
    @After
    public void tearDown(){
        driver.quit();
    }
}
