package test.examples;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import test.base.Base;

public class BrowserInteractions extends Base {

    @Tag("browserInformation()")
    @DisplayName("browserInformation()")
    @Test
    public void browserInformation() {
        driver.get("https://www.saucedemo.com");

        // "Address Book"
        driver.getTitle();

        // "https://saucedemo.com/"
        driver.getCurrentUrl();

        // "<html> ... </html>"
        driver.getPageSource();
    }
    @Tag("browserNavigation()")
    @DisplayName("browserNavigation()")
    @Test
    public void browserNavigation() {
        driver.get("https://www.saucedemo.com");

        driver.navigate().to("http://google.com");
        driver.navigate().refresh();
        driver.navigate().back();
        driver.navigate().forward();
    }
}
