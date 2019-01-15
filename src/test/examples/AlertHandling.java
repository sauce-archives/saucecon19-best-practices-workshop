package test.examples;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.JavascriptExecutor;
import test.base.Base;

public class AlertHandling extends Base {

    @Tag("alertHandling()")
    @DisplayName("alertHandling()")
    @Test
    public void alertHandling() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("alert('Hello World');");

        Alert alert = driver.switchTo().alert();

        // "Hello World"
        alert.getText();

        // Close Alert
        alert.dismiss();
    }

}

