package pages;

import exercises.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;

public class CheckoutPage extends BaseTest {

    public CheckoutPage(WebDriver driver) {
        this.driver = driver;
    }

    public void visit() {
        driver.get("https://www.saucedemo.com/cart.html");
    }

    public void setCartState() {
        driver.navigate().refresh();
        ((JavascriptExecutor)driver).executeScript("window .sessionStorage .setItem('standard-username', 'standard-user')");
        ((JavascriptExecutor)driver).executeScript("window .sessionStorage .setItem('cart-contents', '[4,1]')");
        driver.navigate().refresh();
    }

    public void checkout() {
        String checkoutLink = "cart_checkout_link";
        driver.findElement(By.className(checkoutLink)).click();
    }

    public Boolean hasItems() {
        String cartBadge = "shopping_cart_badge";
        return Integer.parseInt(driver.findElement(By.className(cartBadge)).getText()) > 0;
    }
}