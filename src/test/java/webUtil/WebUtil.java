package webUtil;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pageobject.SignInPage;


/**
 * Created by lkarabelnikava on 11/20/2016.
 */
public class WebUtil {
    public static SignInPage goToSignInPage(WebDriver driver) {
        driver.get("http://gmail.com");
        return PageFactory.initElements(driver, SignInPage.class);
    }
}
