package pageobject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * Created by lkarabelnikava on 11/20/2016.
 */
public class EmailHomePage {
    public SignInPage signOut(WebDriver driver) {
        WebElement profileButton = driver.findElement(By.cssSelector(".gb_8a.gbii"));
        profileButton.click();
        WebElement signoutLinkage = driver.findElement(By.id("gb_71"));
        signoutLinkage.click();
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("Email")));
        return PageFactory.initElements(driver, SignInPage.class);

    }

    public boolean isElementExist(WebDriver driver) {
        return driver.findElements(By.id("Email")).size()>0;
    }

    public void clickComposeButton(WebDriver driver) {
        WebElement composeButton = driver.findElement(By.cssSelector("div[role='button'][gh='cm']"));
        composeButton.click();
    }

    public void fillInRecipient(WebDriver driver, String s) {
        WebElement toTextArea = driver.findElement(By.cssSelector("textarea[name='to']"));
        toTextArea.clear();
        toTextArea.sendKeys(s);
    }

    public void fillInSubject(WebDriver driver, String subject) {
        WebElement subjectTextArea = driver.findElement(By.cssSelector("input[placeholder='Subject']"));
        subjectTextArea.clear();
        subjectTextArea.sendKeys(subject);
    }

    public void fillInBody(WebDriver driver, String body) {
        WebElement bodyTextArea = driver.findElement(By.cssSelector("div[aria-label='Message Body'"));

        bodyTextArea.clear();
        bodyTextArea.sendKeys(body);
    }

    public void clickSend(WebDriver driver) {
        //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@aria-label,\"Send\")]")));
        WebElement sendButton = driver.findElement(By.xpath("//div[contains(@aria-label,\"Send\")]"));
        sendButton.click();
    }

    public void clickInboxWithNewEmail(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(@aria-label, 'Inbox')]")));
        WebElement inboxLinkage = driver.findElement(By.xpath("//a[contains(@aria-label, 'Inbox')]"));
        inboxLinkage.click();
    }

    public EmailViewPage clickNewEmail(WebDriver driver) {
        WebDriverWait wait = new WebDriverWait(driver, 30);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("div[class='y6'] span[id] b")));
        WebElement newEmail = driver.findElement(By.cssSelector("div[class='y6'] span[id] b"));
        newEmail.click();
        return  PageFactory.initElements(driver, EmailViewPage.class);

    }
}
