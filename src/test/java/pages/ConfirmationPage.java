package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ConfirmationPage extends BasePage {
    public ConfirmationPage(WebDriver driver) {
        super(driver);
    }
    //TODO the back part of the URL is being duplicated and can probably be extracted into
    //a property that keeps track of checkout-step-two.html
    public void visit()
    {
        driver.navigate().to("https://www.saucedemo.com/checkout-step-two.html");
    }

    public void setPageState()
    {
        ((JavascriptExecutor)driver).executeScript("window.sessionStorage.setItem('standard-username', 'standard-user')");
        ((JavascriptExecutor)driver).executeScript("window.sessionStorage.setItem('cart-contents', '[4,1]')");
        driver.navigate().refresh();
    }
    public Boolean hasItems() {
        String cartBadge = "shopping_cart_badge";
        return Integer.parseInt(driver.findElement(By.className(cartBadge)).getText()) > 0;
    }
    public CheckoutCompletePage FinishCheckout()
    {
        String finished = ".btn_action.cart_button";
        WebElement finishButton = driver.findElement(By.cssSelector(finished));
        finishButton.click();
        return new CheckoutCompletePage(driver);
    }

    public boolean IsLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        return wait.until(ExpectedConditions.urlContains("checkout-step-two.html"));
    }
}
