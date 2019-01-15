package test.examples;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import test.base.Base;

public class JSExecutor extends Base {

    @Tag("jsExecute()")
    @DisplayName("jsExecute()")
    @Test
    public void jsExecute() {
        driver.get("https://www.saucedemo.com");

        WebElement element = driver.findElement(By.className("login-button"));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", element);

        js.executeScript("window.scrollBy(0,50)");
    }

}

