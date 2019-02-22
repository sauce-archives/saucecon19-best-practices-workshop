package exercises;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.xml.dom.Tag;
import pages.LogInPage;

public class LogInTest extends BaseTest  {

    @Tag(name = "logInSuccessfully()")
    @Test
    /** Tests for a successful login **/
    public void logInSuccessfully() {
        LogInPage logInPage = LogInPage.visit(driver);
        logInPage.signInSuccessfully();
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
    }
}
