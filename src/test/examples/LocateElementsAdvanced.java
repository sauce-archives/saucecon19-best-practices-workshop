package test.examples;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.By;
import test.base.Base;

public class LocateElementsAdvanced extends Base {

    @BeforeEach
    public void navigate() {
        driver.get("https://www.saucedemo.com");
    }

    @Tag("locateElementsCSS()")
    @DisplayName("locateElementsCSS()")
    @Test
    public void locateElementsCSS() {
        // CSS for ID
        driver.findElement(By.cssSelector("#login_button_container"));

        // CSS for Class Name
        driver.findElement(By.cssSelector(".login-input"));

       /* // CSS for Element with Multiple Classes
        driver.findElement(By.cssSelector(".login-input.login-button"));*/

        // CSS for Attribute
        driver.findElement(By.cssSelector("input[value='LOGIN']"));

        // CSS for Multiple Attributes
        driver.findElement(By.cssSelector("input[type='submit'][class='login-button']"));
    }
    @Tag("locateElementCSSHierarchy()")
    @DisplayName("locateElementCSSHierarchy()")
    @Test
    public void locateElementCSSHierarchy() {
        // descendant
        driver.findElement(By.cssSelector(".login-box form"));

        // direct descendant (child)
        driver.findElement(By.cssSelector(".login-box > form > .login-input"));

        // first child
        driver.findElement(By.cssSelector(".login-box:first-child"));

        // last child
        driver.findElement(By.cssSelector(".login-box:last-child"));

        // second child
        driver.findElement(By.cssSelector(".login-box:nth-child(1)"));

        // second child from the end
        driver.findElement(By.cssSelector(".login-box:nth-last-child(1)"));
    }

    @Tag("locateElementCSSAttrSubString()")
    @DisplayName("locateElementCSSAttrSubString()")
    @Test
    public void locateElementCSSAttrSubString() {
        // attribute starts with Text
        driver.findElement(By.cssSelector("input[data-test^=user]"));

        // attribute ends with Text
        driver.findElement(By.cssSelector("input[data-test$=name]"));

        // attribute contains Text
        driver.findElement(By.cssSelector("input[data-test*=me]"));
    }

    @Tag("locateElementCSSSibling()")
    @DisplayName("locateElementCSSSibling()")
    @Test
    public void locateElementCSSSibling() {
        // immediate previous sibling
        driver.findElement(By.cssSelector("input + input"));

        // previous sibling
        driver.findElement(By.cssSelector("input ~ input"));
    }

}