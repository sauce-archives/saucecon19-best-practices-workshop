package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class CheckoutCompletePage extends BasePage{

    public CheckoutCompletePage(WebDriver driver) {
        super(driver);
    }

    public boolean IsLoaded()
    {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        return wait.until(ExpectedConditions.urlContains("checkout-complete.html"));
    }
}
