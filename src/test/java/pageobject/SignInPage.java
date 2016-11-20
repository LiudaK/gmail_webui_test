package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

/**
 * Created by lkarabelnikava on 11/20/2016.
 */
public class SignInPage {
    public void fillInUserName(WebDriver driver, String s) {
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        WebElement usernameTextbox = driver.findElement(By.id("Email"));
        usernameTextbox.clear();
        usernameTextbox.sendKeys(s);
        driver.findElement(By.id("next")).click();

        //synchronization, Explicit wait
        WebDriverWait wait = new WebDriverWait(driver, 30);

        WebElement checkbox = driver.findElement(By.id("PersistentCookie"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("PersistentCookie")));
        checkbox.click();
    }

    public void fillInPassword(WebDriver driver, String s) {
        WebElement passwordTextbox = driver.findElement(By.id("Passwd"));
        passwordTextbox.clear();
        passwordTextbox.sendKeys(s);
    }

    public EmailHomePage clickSignIn(WebDriver driver) {
        WebElement signInButton = driver.findElement(By.id("signIn"));
        signInButton.click();
        driver.findElement(By.partialLinkText("Inbox"));

        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText("Inbox")));
        return PageFactory.initElements(driver, EmailHomePage.class);

    }
}
