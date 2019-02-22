package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class BasePage {
    WebDriver driver;

    void click(By locator) {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        findBy(locator).click();
    }
    void sendKeys(By locator, String text) {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        findBy(locator).sendKeys(text);
    }
    WebElement findBy(By locator) {
        return driver.findElement(locator);
    }
    public String getInnerText(By locator) { return findBy(locator).getText();}

}
