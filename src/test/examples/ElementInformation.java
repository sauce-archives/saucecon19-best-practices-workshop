package test.examples;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import test.base.Base;

public class ElementInformation extends Base {

    @Tag("signInLink")
    @DisplayName("signInLink()")
    @Test
    public void signInLink() {
        driver.get("https://www.saucedemo.com");

        // Gather the Text of Element
        driver.findElement(By.id("login_button_container")).getText();

        // Gather the Tag Name
        driver.findElement(By.id("login_button_container")).getTagName();

        // Gather the Attribute Data
        driver.findElement(By.id("login_button_container")).getAttribute("data-test");

        // Determine if Element Rendered on Page
        driver.findElement(By.id("login_button_container")).isDisplayed();

        // Determine if Actions can be Performed on Element
        driver.findElement(By.id("login_button_container")).isEnabled();
    }

}