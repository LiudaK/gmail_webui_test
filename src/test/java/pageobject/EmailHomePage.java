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
}
