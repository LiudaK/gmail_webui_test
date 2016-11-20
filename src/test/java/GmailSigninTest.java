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
//    @After
//    public void tearDown(){
//        driver.quit();
//    }
}
