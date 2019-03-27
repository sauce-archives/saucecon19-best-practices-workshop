package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class InventoryPage extends BasePage{

    public InventoryPage(WebDriver driver) {
        super(driver);
    }

    public boolean IsLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement logo = driver.findElement(By.className("app_logo"));
        return wait.until(ExpectedConditions.visibilityOf(logo)).isDisplayed();
    }
}
