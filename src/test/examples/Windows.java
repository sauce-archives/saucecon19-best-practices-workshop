package test.examples;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.JavascriptExecutor;
import test.base.Base;

import java.util.Set;

public class Windows extends Base {

    @Tag("windowSwitching()")
    @DisplayName("windowSwitching()")
    @Test
    public void windowSwitching() {
        driver.get("https://google.com");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.open('https://www.saucedemo.com');");

        String origWindow = driver.getWindowHandle();
        Set handles = driver.getWindowHandles();
        handles.remove(origWindow);

        String nextWindow = String.valueOf(handles.iterator().next());

        // "Example Domain"
        driver.switchTo().window(nextWindow);

        driver.close();

        // "Google"
        driver.switchTo().window(origWindow);
    }

}

