package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class LoginPage extends BasePage {

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage visit() {
        driver.get("https://www.saucedemo.com");
        return this;
    }

    public InventoryPage login(String username, String password)
    {
        String userField = "[data-test='username']";
        String passField = "[data-test='password']";
        String loginBtn = "[value='LOGIN']";

        driver.findElement(By.cssSelector(userField)).sendKeys(username);
        driver.findElement(By.cssSelector(passField)).sendKeys(password);
        driver.findElement(By.cssSelector(loginBtn)).click();
        return new InventoryPage(driver);
    }

    public boolean IsLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement sauceBot = driver.findElement(By.className("bot_column"));
        return wait.until(ExpectedConditions.visibilityOf(sauceBot)).isDisplayed();
    }
}
